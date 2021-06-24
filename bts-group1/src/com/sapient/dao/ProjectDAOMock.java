package com.sapient.dao;

import java.sql.SQLException;
import java.util.List;

import com.sapient.model.Project;

public class ProjectDAOMock implements IProjectDAO {
	@Override
	public List<Project> selectAllProjects() {
		return null;
	}

	@Override
	public void saveProject(Project newproejct) {

	}

	@Override
	public void insertProject(Project project) throws SQLException {
	
		
	}

	@Override
	public Project selectProject(int id) {
		
		return null;
	}
	@Override
	public boolean updateProject(Project project) throws SQLException {
		
		return false;
	}

}
