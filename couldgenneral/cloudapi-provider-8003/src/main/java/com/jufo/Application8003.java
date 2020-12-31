package com.jufo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
@ComponentScan(value= {"com.jufo.*"})
@EnableEurekaClient
@EnableDiscoveryClient
//@MapperScan(basePackages= {"com.jufo.mappers"})
//@EnableTransactionManagement
@EnableConfigurationProperties
//@SpringBootApplication
public class Application8003 {

	public static void main(String[] args) {
		SpringApplication.run(Application8003.class, args);
	}

}