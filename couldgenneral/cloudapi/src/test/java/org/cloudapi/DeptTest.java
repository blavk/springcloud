package org.cloudapi;

import org.cloudapi.entity.Dept;
import org.junit.Test;

import com.google.gson.Gson;

public class DeptTest {
	
	Gson gson = new Gson();
	
	@Test
	public void test1() {
		Dept dept = new Dept(4L,"dept4","mysql");
		String json = gson.toJson(dept);
		System.out.println(json);
	}

}
