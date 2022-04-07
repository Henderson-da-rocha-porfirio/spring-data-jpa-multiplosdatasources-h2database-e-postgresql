package com.tuyo.multiplosdatasources.config;

import com.tuyo.multiplosdatasources.produto.entities.Produto;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration						// Usar a anotação @Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "produtoEntityManagerFactory", transactionManagerRef = "produtoTransactionManager", basePackages = {
		"com.tuyo.multiplosdatasources.produto" })
public class ProdutoDataSourceConfig {

	@Bean(name = "produtoDataSourceProperties")
	@ConfigurationProperties("spring.datasource-produto")
	public DataSourceProperties produtoDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "produtoDataSource")
	public DataSource produtoDataSource() {
		return produtoDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "produtoEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean produtoEntityManagerFactory(EntityManagerFactoryBuilder builder) {

		HashMap<String, String> produtoJpaProperties = new HashMap<>();					// Para fazer Customizações ou passar alguma propriedade do Hibernate, como criar ou 'dropar' tabelas automaticamente. HashMap faz a controller ir a uma nova variável local chamada produtoJpaProperties
		produtoJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");			// Isso automaticamente cria tabelas.
		return builder.dataSource(produtoDataSource()).packages(Produto.class).persistenceUnit("produtoDataSource")
				.properties(produtoJpaProperties).build();
	}

	@Bean(name = "produtoTransactionManager")
	public PlatformTransactionManager produtoTransactionManager(
			@Qualifier("produtoEntityManagerFactory") EntityManagerFactory produtoEntityManagerFactory) {
		return new JpaTransactionManager(produtoEntityManagerFactory);
	}

}
