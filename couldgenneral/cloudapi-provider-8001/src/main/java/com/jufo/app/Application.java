package com.jufo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
@ComponentScan(value= {"com.jufo.*"})
//@MapperScan(basePackages= {"com.jufo.mappers"})
//@EnableTransactionManagement
@EnableConfigurationProperties
//@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}