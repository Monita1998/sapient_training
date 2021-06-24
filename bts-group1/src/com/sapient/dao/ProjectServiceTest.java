package com.sapient.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProjectServiceTest {
	private IProjectService service;

	@Before
	public void setup() {
		service = ProjectService.getInstance();
	}

	@Test
	public void testGetInstance() {
		assertNotNull(service);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSaveProject() {
		service.save(null);
	}

}

