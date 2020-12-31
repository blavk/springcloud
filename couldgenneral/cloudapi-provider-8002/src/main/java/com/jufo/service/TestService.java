package com.jufo.service;

import java.awt.List;
import java.util.ArrayList;

import org.cloudapi.entity.Dept;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;

@Component
public class TestService {
	
	static Cache<Long, Dept> cache;
	static {
		cache = CacheBuilder.newBuilder().initialCapacity(100).build();
		cache.put(1L, new Dept(1L, "dept1", "8002"));
		cache.put(2L, new Dept(2L, "dept2", "8002"));
		cache.put(3L, new Dept(3L, "dept3", "8002"));
		
	}
	
	public ArrayList<Dept> getAllDepts() {
		return Lists.newArrayList(cache.asMap().values());
		
	}

	public Dept getDept(long id) {
		return cache.getIfPresent(id);
	}

	public boolean addDept(Dept dept) {
		cache.put(dept.getDno(), dept);
		return true;
	}

}
