package com.jufo.controller;

import java.util.List;

import org.cloudapi.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DeptConsumerController {
	
	private static final String URL_PREFIX = "http://127.0.0.1:8001";
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="/consumer/dept/add", method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return restTemplate.postForObject(URL_PREFIX + "/dept/add", dept, Boolean.class);
	}
	
	@RequestMapping(value="/consumer/dept/list", method=RequestMethod.GET)
	public List<Dept> list() {
		return restTemplate.getForObject(URL_PREFIX + "/dept/list", List.class);
	}
	
	@RequestMapping(value="/consumer/dept/get/{id}", method=RequestMethod.GET)
	public Dept get(@PathVariable(value="id") long id) {
		return restTemplate.getForObject(URL_PREFIX + "/dept/get/" + id, Dept.class);
	}

}
