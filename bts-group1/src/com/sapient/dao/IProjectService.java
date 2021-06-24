package com.sapient.dao;

import java.util.List;

import com.sapient.model.Project;

public interface IProjectService {

	void save(Project newproject);

	Project selectProject(int id);

	List<Project> selectAllProjects();
	

}