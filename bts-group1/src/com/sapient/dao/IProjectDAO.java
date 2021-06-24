package com.sapient.dao;

import java.sql.SQLException;
import java.util.List;

import com.sapient.model.Project;

public interface IProjectDAO {

	void insertProject(Project project) throws SQLException;

	Project selectProject(int id);

	List<Project> selectAllProjects();

	boolean updateProject(Project project) throws SQLException;

	void saveProject(Project newproject);

}