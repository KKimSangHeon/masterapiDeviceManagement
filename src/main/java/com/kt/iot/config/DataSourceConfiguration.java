/*
f * GiGA IoT Platform version 2.0
 *
 *  Copyright ⓒ 2015 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */

package com.kt.iot.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.hibernate.service.ServiceRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DataSource Configuration
 * 
 * @author Sangsun Park (blue.park@kt.com)
 *
 */
@Configuration
@EnableTransactionManagement
//@MapperScan(basePackages = {"**.v10.**.mapper.**", "**.v11.**.mapper.**"})
@MapperScan(basePackages = { "**.mapper.**" })
public class DataSourceConfiguration {

	@Value("${jdbc.driverClassName:net.sf.log4jdbc.DriverSpy}")
	private String driverClassName;

//    @Value("${jdbc.url:jdbc:log4jdbc:postgresql://localhost:5432/psmcdb}")
	private String jdbcUrl;

//    @Value("${jdbc.username:openp}")
	private String jdbcUserName;

//    @Value("${jdbc.password:openp}")
	private String jdbcPassword;

	@Value("${dbcp.max.total:8}")
	private int maxTotal;
	@Value("${dbcp.max.idle:8}")
	private int maxIdle;

	@Bean
	public DataSource dataSource() {

		handleDataSourceParam();

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUserName);
		dataSource.setPassword(jdbcPassword);
		dataSource.setValidationQuery("SELECT 1");
		dataSource.setTestOnBorrow(true);
		dataSource.setTestOnReturn(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(1800000);
		dataSource.setMaxTotal(maxTotal);
		dataSource.setMaxIdle(maxIdle);

		System.out.println("setPassword:" + jdbcPassword);
		return dataSource;
	}

	private void handleDataSourceParam() {
		// TODO Auto-generated method stub

		// -------------------------서버에 배포할때 사용
//    	String host = System.getenv("dbHost");
//    	String port = System.getenv("dbPort");
//    	String dbName = System.getenv("dbName");

		// --------------------------로컬에서 돌릴때만 사용
		String host = "localhost";
		String port = "5432";
		String dbName = "heroes";

		if (host != null && port != null && dbName != null) {
			System.out.println("generating jdbcUrl.....");
			StringBuilder jdbcUrlBuiler = new StringBuilder("jdbc:log4jdbc:postgresql://");
			jdbcUrlBuiler.append(host);
			jdbcUrlBuiler.append(":");
			jdbcUrlBuiler.append(port);
			jdbcUrlBuiler.append("/");
			jdbcUrlBuiler.append(dbName);
			jdbcUrl = jdbcUrlBuiler.toString();
			System.out.println("generated jdbcUrl:>>" + jdbcUrl);
		}

		// --------------------------로컬에서 돌릴때만 사용
		jdbcUserName = "postgres";
		jdbcPassword = "HEROES";

		// -------------------------서버에 배포할때 사용
//    	jdbcUserName = System.getenv("dbUserName");
//    	jdbcPassword = System.getenv("dbUserPassword");

	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis.xml"));
		return bean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	/*
	 * 
	 * 
	 * JPA 관련 세팅
	 * 
	 * 
	 * 
	 */

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.kt.iot");
		factory.setDataSource(dataSource);
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
//        factory.setJpaProperties(jpaProperties());
		factory.afterPropertiesSet();

		return factory;
	}

	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {

		org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
		System.out.println("Hibernate Configuration loaded");
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");

		// apply configuration property settings to StandardServiceRegistryBuilder
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		System.out.println("Hibernate serviceRegistry created");
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		return sessionFactory;
	}
}
