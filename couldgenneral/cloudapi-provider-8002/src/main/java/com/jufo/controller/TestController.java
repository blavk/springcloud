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
	public Dept get(@PathVariable(value="id") long id) {
		return testService.getDept(id);
	}
	
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
