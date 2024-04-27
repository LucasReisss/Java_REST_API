package com.example.demo.integrationtests.testcontainers;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.aot.AotApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

	static class Initializer implements AotApplicationContextInitializer<ConfigurableApplicationContext> {
		static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.3.0");
		
		private static void startContainers() {
			Startables.deepStart(Stream.of(mysql)).join();
		}
		
		private Map<String, String> createConnectionConfiguration() {
			return Map.of(
					"spring.datasource.url", mysql.getJdbcUrl(),
					"spring.datasource.username", mysql.getUsername(),
					"spring.datasource.password", mysql.getPassword()
					);
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			
			startContainers();
			
			ConfigurableEnvironment environment = applicationContext.getEnvironment();
			
			MapPropertySource testcontainers = new MapPropertySource(
					"testcontainers",
					(Map) createConnectionConfiguration());
			
			environment.getPropertySources().addFirst(testcontainers);
			
		}
		
	}
	
}
