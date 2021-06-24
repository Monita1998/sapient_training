package com.sapient.dao;

import java.sql.SQLException;
import java.util.List;

import com.sapient.model.Bug;

public interface IBugDAO {

	void saveBug(Bug newbug);

	List<Bug> selectAllBugs();

	Bug selectBug(int id);

	boolean updateBug(Bug updatebug) throws SQLException;

}