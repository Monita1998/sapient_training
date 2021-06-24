package com.sapient.dao;

import java.util.List;

import com.sapient.model.Project;

public class ProjectService implements IProjectService {
	IProjectDAO projectDAO = ProjectDAO.getInstance();
	private static IProjectService projectService;

	public static IProjectService getInstance() {
		if (projectService == null) {
			projectService = new ProjectService();
		}
		return projectService;
	}

	@Override
	public void save(Project newproject) {
		if (newproject == null) {
			throw new IllegalArgumentException();
		}
		List<Project> allproject= projectDAO.selectAllProjects();
		for (Project project : allproject) {
			if(project.getName().equalsIgnoreCase(newproject.getName())){
				throw new IllegalArgumentException("Project with this name already exist");
			}
			
		}
		projectDAO.saveProject(newproject);
	}

	@Override
	public Project selectProject(int id) {
		return projectDAO.selectProject(id);

	}

	@Override
	public List<Project> selectAllProjects() {
		return projectDAO.selectAllProjects();
	}

}