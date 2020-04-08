package com.odontoweb.arquitetura.multitenant.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.odontoweb.arquitetura.persistence.CurrentTenantIdentifierResolverImpl;
import com.odontoweb.arquitetura.persistence.MultiTenantProvider;

@Configuration
public class MultiTenatConfig {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JpaProperties jpaProperties;
	
	@Autowired
	private MultiTenantProvider multiTenantConnectionProvider;
	
	@Autowired
	private CurrentTenantIdentifierResolverImpl currentTenantIdentifierResolver;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		Map<String, Object> hibernateProps = new LinkedHashMap<>();
		
		hibernateProps.putAll(jpaProperties.getProperties());

		hibernateProps.put(Environment.FORMAT_SQL, true);
		hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
		hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
		hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
		hibernateProps.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

		return builder.dataSource(dataSource)
					.packages("com.odontoweb.microservice.model")
					.properties(hibernateProps)
					.jta(false)
					.build();
	}
	
	@Bean
	public MultiTenantProvider multiTenantProvider(DataSource dataSource){
		return new MultiTenantProvider(dataSource);
	}
	
	@Bean
	public CurrentTenantIdentifierResolverImpl currentTenantIdentifierResolverImpl(){
		return new CurrentTenantIdentifierResolverImpl();
	}
	
}
