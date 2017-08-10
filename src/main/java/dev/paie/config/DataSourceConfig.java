package dev.paie.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:app.properties")
public class DataSourceConfig {

	 @Value("${db.driver}")
	 String driver;
	 @Value("${db.url}")
	 String url;
	 @Value("${db.user}")
	 String user;
	 @Value("${db.pwd}")
	 String pwd;


	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(pwd);
		return dataSource;
	}
	
	 @Bean
     public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
     }

}
