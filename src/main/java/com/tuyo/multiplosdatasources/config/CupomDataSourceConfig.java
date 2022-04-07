package com.tuyo.multiplosdatasources.config;

import com.tuyo.multiplosdatasources.cupom.entities.Cupom;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration		// Usar a anotação @Configuration
@EnableTransactionManagement														// Habilita a transação: EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "cupomEntityManagerFactory",		// @EnableJpaRepositories - faz referência EntityManagerFactory com entityManagerFactoryRef
transactionManagerRef = "cupomTransactionManager", basePackages = {					// Agora ele pega o TransactionManager que pega o controle da base da transação
		"com.tuyo.multiplosdatasources.cupom" })									// Aqui ele pega o pacote(package) base do projeto. Os pacotes serão escaneados e agirão conforme o esperado.
public class CupomDataSourceConfig {

	@Primary 												// @Primary = significa que é o primeiro datasource que estou configurando. E todos os métodos serão marcados como primários e terão poder de controlar um import e etc.
	@Bean(name = "cupomDataSourceProperties")				// Beans podem receber os nomes com "".
	@ConfigurationProperties("spring.datasource-cupom")  // informa que estamos utilizando as configs do application.properties referente. E providenciamos a chave: spring.datasource-cupom
	public DataSourceProperties cupomDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "cupomDataSource")
	public DataSource cupomDataSource() {					// DataSource Builder será criado para criar um data source e especificar o seu tipo.
		return cupomDataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();		// Especifica que tem um datasource tipo Hikari
	}

	@Primary												// EntityManagerFactory config
	@Bean(name = "cupomEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean cupomEntityManagerFactoryBuilder(EntityManagerFactoryBuilder builder) {	//Aqui é retornado um container Local.
		return builder.dataSource(cupomDataSource()).packages(Cupom.class).build();
	}

	@Primary
	@Bean(name = "cupomTransactionManager")					// Criado o TransactionManager
	public PlatformTransactionManager cupomTransactionManager(
			@Qualifier("cupomEntityManagerFactory") EntityManagerFactory cupomEntityManagerFactory) {		// @Qualifier = é usado alí dentro para especificar o nome Bean de de cupomEntityManagerFactory. A fim de que esse seja injetado automaticamente alí.
		return new JpaTransactionManager(cupomEntityManagerFactory);										// Toda a conversão que está ocorrendo aqui, significa que estamos pegando de uma entidade container local: LocalContainerEntityManagerFactoryBean alí de cima,
	}																										// E daí o Spring embrulha (to wrap) tudo isso com cuidado e passa neste método EntityManagerFactory.

}
