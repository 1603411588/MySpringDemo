package com.liuyi;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = Controller.class) })
@PropertySources({ @PropertySource("classpath:jdbc.properties"), @PropertySource("classpath:mail.properties") })
public class JavaConfig implements EnvironmentAware {

	private Environment env;

	@Bean
	public DataSource dataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));
		dataSource.setDefaultAutoCommit(env.getProperty("database.defaultAutoCommit", boolean.class));
		dataSource.setMaxActive(env.getProperty("database.maxActive", int.class));
		dataSource.setFilters(env.getProperty("database.filters"));
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}

}
