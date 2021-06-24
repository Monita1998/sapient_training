package com.sapient.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.sapient.model.Project;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 *
 */
public class ProjectDAO implements IProjectDAO  {
	private static IProjectDAO projectDAO;
	Logger logger = Logger.getLogger(ProjectDAO.class.getClass().toString());

	public static IProjectDAO getInstance() {
		if (projectDAO == null) {
			projectDAO = new ProjectDAO();

		}
		return projectDAO;
	}

	private String jdbcURL = "jdbc:mysql://localhost:3306/bts";
	private String jdbcUsername = "root";
	private String jdbcPassword = "admin";

	private static final String INSERT_PROJECT_SQL = "INSERT INTO project" + " (name,description ) VALUES "
			+ " (?, ?);";

	private static final String SELECT_PROJECT_BY_ID = "select id,name,description from project where id =?";
	private static final String SELECT_ALL_PROJECT = "select * from project";
	private static final String DELETE_PROJECT_SQL = "delete from project where id = ?;";
	private static final String UPDATE_PROJECT_SQL = "update project set name = ?,description= ? where id = ?;";

	public ProjectDAO() {
		
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {

			logger.warning(e.getMessage());
		} catch (ClassNotFoundException e) {

			logger.warning(e.getMessage());
		}
		return connection;
	}

	@Override
	public void insertProject(Project project) throws SQLException {
		logger.info(INSERT_PROJECT_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT_SQL)) {
			preparedStatement.setString(1, project.getName());
			preparedStatement.setString(2, project.getDescription());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
	}

	@Override
	public Project selectProject(int id) {
		Project project = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROJECT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				project = new Project(id, name, description);
			}
		} catch (SQLException e) {
			logger.warning(e.toString());
		}
		return project;
	}

	@Override
	public List<Project> selectAllProjects() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Project> project1 = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROJECT);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				project1.add(new Project(id, name, description));
			}
		} catch (SQLException e) {
			logger.warning(e.toString());
		}
		return project1;
	}


	@Override
	public boolean updateProject(Project project) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PROJECT_SQL);) {
			statement.setString(1, project.getName());
			statement.setString(2, project.getDescription());
			statement.setInt(3, project.getId());
		
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	@Override
	public void saveProject(Project newproject) {
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT_SQL)) {

			preparedStatement.setString(1, newproject.getName());
			preparedStatement.setString(2, newproject.getDescription());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
