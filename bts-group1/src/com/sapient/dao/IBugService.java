package com.sapient.dao;

import java.sql.SQLException;
import java.util.List;

import com.sapient.model.Bug;

public interface IBugService {

	void save(Bug newbug);

	Bug selectBug(int id);

	List<Bug> selectAllBugs();

	boolean updateBug(Bug updatebug) throws SQLException;

	void setBugDAO(BugDAOMock mockbugdao);

	

}