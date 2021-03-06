package com.jufo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;

import com.rule.MyRuleConfig;

@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
@ComponentScan(value= {"com.jufo.*"})
@EnableConfigurationProperties
@EnableEurekaClient
@RibbonClient(name="CLOUD-DEPT",configuration=MyRuleConfig.class)
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}