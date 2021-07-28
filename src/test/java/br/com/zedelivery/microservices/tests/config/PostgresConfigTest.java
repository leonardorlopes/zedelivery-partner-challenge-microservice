package br.com.zedelivery.microservices.tests.config;

import static java.util.Objects.nonNull;

import javax.sql.DataSource;

import org.junit.ClassRule;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.testcontainers.containers.PostgreSQLContainer;

@Lazy
@Configuration
@EnableAutoConfiguration
public class PostgresConfigTest {

	@SuppressWarnings("rawtypes")
	@ClassRule
	private static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11-alpine");

	static {
		postgreSQLContainer.withDatabaseName("partners");
		postgreSQLContainer.withUsername("postgres");
		postgreSQLContainer.withPassword("admin");
		postgreSQLContainer.start();
	}

	@SuppressWarnings("rawtypes")
	@Bean
	@Scope("singleton")
	public PostgreSQLContainer postgreSQLContainer() {
		return postgreSQLContainer;
	}

	@Bean
	@Scope("singleton")
	public DataSource dataSource() {
		return DataSourceBuilder.create().url(postgreSQLContainer.getJdbcUrl())
				.username(postgreSQLContainer.getUsername()).password(postgreSQLContainer.getPassword())
				.driverClassName(postgreSQLContainer.getDriverClassName()).build();
	}

	public String getJdbcUrl() {
		return nonNull(postgreSQLContainer) ? postgreSQLContainer.getJdbcUrl() : null;
	}

}
