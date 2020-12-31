package org.cloudapi.service;

import java.util.List;

import org.cloudapi.entity.Dept;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>{

	@Override
	public DeptClientService create(Throwable cause) {
		return new DeptClientService() {
			
			@Override
			public List<Dept> list() {
				return null;
			}
			
			@Override
			public Dept get(long id) {
				return new Dept(id,"id " + id + " has no dept info!","not exist");
			}
			
			@Override
			public boolean add(Dept dept) {
				return false;
			}
		};
	}

}
