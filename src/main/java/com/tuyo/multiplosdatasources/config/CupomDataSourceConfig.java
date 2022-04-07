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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "couponEntityManagerFactory", 
transactionManagerRef = "couponTransactionManager", basePackages = {
		"com.bharath.springdatajpa.cupom" })
public class CupomDataSourceConfig {

	@Primary
	@Bean(name = "couponDataSourceProperties")
	@ConfigurationProperties("spring.datasource-cupom")
	public DataSourceProperties couponDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "couponDataSource")
	public DataSource couponDataSource() {
		return couponDataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}

	@Primary
	@Bean(name = "couponEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean couponEntityManagerFactoryBuilder(
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(couponDataSource()).packages(Cupom.class).build();
	}

	@Primary
	@Bean(name = "couponTransactionManager")
	public PlatformTransactionManager couponTransactionManager(
			@Qualifier("couponEntityManagerFactory") EntityManagerFactory couponEntityManagerFactory) {
		return new JpaTransactionManager(couponEntityManagerFactory);
	}

}
