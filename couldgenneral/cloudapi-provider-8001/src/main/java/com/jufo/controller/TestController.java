package com.jufo.controller;

import java.util.ArrayList;
import java.util.List;

import org.cloudapi.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jufo.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class TestController {
	
	@Autowired
	TestService testService;
	
	@Autowired
	DiscoveryClient client;
	
	@RequestMapping(value="/dept/add", method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return testService.addDept(dept);
	}
	
	@RequestMapping(value="/dept/list", method=RequestMethod.GET)
	public List<Dept> list() {
		return testService.getAllDepts();
	}
	
	@RequestMapping(value="/dept/get/{id}", method=RequestMethod.GET)
//	@HystrixCommand(fallbackMethod="hystrixGet")
	public Dept get(@PathVariable(value="id") long id) {
		Dept dept = testService.getDept(id);
		if (dept == null) {
			throw new RuntimeException("id: " + id + " has no dept info! ");
		}
		return dept;
	}
	
//	public Dept hystrixGet(@PathVariable(value="id") long id) {
//		return new Dept(id,"id " + id + " has no dept info!","not exist");
//	}
	
	@RequestMapping(value="/dept/discovery", method=RequestMethod.GET)
	public Object getClient() {
		List<String> services = client.getServices();
		System.out.println("**********" + services);
		List<ServiceInstance> instances = client.getInstances("CLOUD-DEPT");
		for (ServiceInstance instance : instances) {
			System.out.println(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
		}
		return client;
	}

}
