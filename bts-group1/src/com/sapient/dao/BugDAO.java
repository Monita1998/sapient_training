package com.sapient.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.sapient.model.Bug;

public class BugDAO implements IBugDAO {
	private static IBugDAO bugDAO;
	 Logger logger = Logger.getLogger(BugService.class.getClass().toString());

	public static IBugDAO getInstance() {
		if (bugDAO == null) {
			bugDAO = new BugDAO();

		}
		return bugDAO;
	}

	static final String DB_URL = "jdbc:mysql://localhost/bts";
	static final String USER = "root";
	static final String PASS = "admin";
	private static final String INSERT_USERS_SQL = "INSERT INTO bug"
			+ "  (project_id,name, description,email,owner,priority,status,bugtype) VALUES "
			+ "(?,?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_ALL_USERS = "select * from bug";
	private static final String SELECT_USER_BY_ID = "select * from bug where id =?";
	private static final String UPDATE_BUG_SQL = "update bug set name = ?,description= ?, "
			+ "email= ?,owner= ?,priority= ?,status= ?,bugtype =?,project_id=? where id = ?;";

	protected  Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.warning(e.getMessage());
		}
		return connection;
	}

	@Override
	public void saveBug(Bug newbug) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setInt(1, newbug.getProject_id());
			preparedStatement.setString(2, newbug.getName());
			preparedStatement.setString(3, newbug.getDescription());
			preparedStatement.setString(4, newbug.getEmail());
			preparedStatement.setString(5, newbug.getOwner());
			preparedStatement.setString(6, newbug.getPriority().toString());
			preparedStatement.setString(7, newbug.getStatus().toString());
			preparedStatement.setString(8, newbug.getBugtype().toString());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
	}

	@Override
	public List<Bug> selectAllBugs() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Bug> bugs = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {

				int id = rs.getInt("id");
				int project_id = rs.getInt("project_id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String email = rs.getString("email");
				String owner = rs.getString("owner");
				String priority = rs.getString("priority");
				String bugtype = rs.getString("bugtype");
				String status = rs.getString("status");

				bugs.add(new Bug(id, project_id, name, description, email, owner, priority, status, bugtype));
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		return bugs;
	}

	@Override
	public Bug selectBug(int id) {
		Bug bug = null;
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int project_id = rs.getInt("project_id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String email = rs.getString("email");
				String owner = rs.getString("owner");
				String priority = rs.getString("priority");
				String bugtype = rs.getString("bugtype");
				String status = rs.getString("status");
				bug = new Bug(id, project_id, name, description, email, owner, priority, status, bugtype);
			}
		} catch (SQLException e) {
			logger.warning(e.getMessage());
		}
		System.out.println("printbug" + bug.getId());
		return bug;
	}

	@Override
	public boolean updateBug(Bug updatebug) throws SQLException {

		boolean rowUpdated;

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_BUG_SQL);) {
			statement.setString(1, updatebug.getName());
			statement.setString(2, updatebug.getDescription());
			statement.setString(3, updatebug.getEmail());
			statement.setString(4, updatebug.getOwner());
			statement.setString(5, updatebug.getPriority().toString());
			statement.setString(6, updatebug.getStatus().toString());
			statement.setString(7, updatebug.getBugtype().toString());
			statement.setInt(8, updatebug.getProject_id());
			statement.setInt(9, updatebug.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;

	}
}