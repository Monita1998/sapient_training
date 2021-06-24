package com.sapient.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sapient.model.Bug;

public class BugServiceTest {
	private IBugService service;
	private BugDAOMock mockbugdao;
	
	@Before
	public void setup() {
		service = BugService.getInstance();
		mockbugdao=new BugDAOMock();
		service.setBugDAO(mockbugdao);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInsertUserNoEmail() { //negative testing
		Bug user = new Bug();
		user.setEmail("usmancom");
		service.save(user);
	}
}
