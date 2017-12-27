package com.gym.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.gym.db.DataService;

public class Base {
	@Autowired
	public DataService dataService;
	
}
