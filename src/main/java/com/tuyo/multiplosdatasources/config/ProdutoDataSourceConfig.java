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
@EnableJpaRepositories(entityManagerFactoryRef = "productEntityManagerFactory", transactionManagerRef = "productTransactionManager", basePackages = {
		"com.bharath.springdatajpa.produto" })
public class ProdutoDataSourceConfig {

	@Bean(name = "productDataSourceProperties")
	@ConfigurationProperties("spring.datasource-produto")
	public DataSourceProperties productDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "productDataSource")
	public DataSource productDataSource() {
		return productDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "productEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(EntityManagerFactoryBuilder builder) {

		HashMap<String, String> productJpaProperties = new HashMap<>();
		//productJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		return builder.dataSource(productDataSource()).packages(Produto.class).persistenceUnit("productDataSource")
				.properties(productJpaProperties).build();
	}

	@Bean(name = "productTransactionManager")
	public PlatformTransactionManager productTransactionManager(
			@Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory) {
		return new JpaTransactionManager(productEntityManagerFactory);
	}

}
