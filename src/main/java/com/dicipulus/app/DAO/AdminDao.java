package com.dicipulus.app.DAO;

import javax.sql.DataSource;

import com.dicipulus.app.model.Admin;

public interface AdminDao {
	public void setDataSource (DataSource ds);
	
	/*
	 * there is only one administrator
	 */
	public Admin getAdmin();
	
	public void changePassword(String password);
}