package com.jufo.controller;

import java.util.List;

import org.cloudapi.entity.Dept;
import org.cloudapi.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeptConsumerController {
	
	@Autowired
	private DeptClientService deptClientService;
	
	@RequestMapping(value="/consumer/dept/add", method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return this.deptClientService.add(dept);
	}
	
	@RequestMapping(value="/consumer/dept/list", method=RequestMethod.GET)
	public List<Dept> list() {
		return deptClientService.list();
	}
	
	@RequestMapping(value="/consumer/dept/get/{id}", method=RequestMethod.GET)
	public Dept get(@PathVariable(value="id") long id) {
		return deptClientService.get(id);
	}
}
