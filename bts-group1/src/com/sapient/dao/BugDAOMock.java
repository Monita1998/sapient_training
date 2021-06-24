package com.sapient.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sapient.model.Bug;

public class BugDAOMock implements IBugDAO {

	@Override
	public void saveBug(Bug newbug){
		newbug.setId(4);

	}

	@Override
	public List<Bug> selectAllBugs() {
		List<Bug> bugs =new ArrayList<>();
		bugs.add(new Bug());
		bugs.add(new Bug());
		return bugs;
	}

	@Override
	public Bug selectBug(int id) {
		Bug bug =new Bug();
		bug.setId(4);
		
		return bug;
	}

	@Override
	public boolean updateBug(Bug updatebug) throws SQLException {
		return false;
	}
}
