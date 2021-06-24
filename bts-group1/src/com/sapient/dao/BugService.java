package com.sapient.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.sapient.model.Bug;

public class BugService implements IBugService {// singleton
	IBugDAO bugDAO = BugDAO.getInstance();
	private static IBugService bugService;

	public static IBugService getInstance() {
		if (bugService == null) {
			bugService = new BugService();
		}
		return bugService;
	}
	

	@Override
	public void save(Bug newbug) {

		String regex = "^(.+)@(.+)$";
		// Compile regular expression to get the pattern
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(newbug.getEmail());
		boolean result = matcher.matches();
		if (!result) {// validations
			throw new IllegalArgumentException("Email is not formated");
		}
		bugDAO.saveBug(newbug);
	}

	@Override
	public Bug selectBug(int id) {
		return bugDAO.selectBug(id);

	}

	@Override
	public List<Bug> selectAllBugs() {
		return bugDAO.selectAllBugs();
	}

	@Override
	public boolean updateBug(Bug updatebug) throws SQLException {
		return bugDAO.updateBug(updatebug);
	}

	

	@Override
	public void setBugDAO(BugDAOMock mockbugdao) {
	this.bugDAO = bugDAO;
		
	}


}
