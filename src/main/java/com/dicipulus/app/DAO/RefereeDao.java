package com.dicipulus.app.DAO;

import javax.sql.DataSource;

import com.dicipulus.app.model.*;

public interface RefereeDao {
	public void setDataSource (DataSource ds);
	
	public Referee getRefereeByUid( String uid);
	
	public void changePassword(String uid,String password);
}