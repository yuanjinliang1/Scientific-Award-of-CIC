package com.dicipulus.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.dicipulus.app.DAO.AdminDao;
import com.dicipulus.app.DAO.RefereeDao;
import com.dicipulus.app.model.*;

public class SystemServiceImp implements SystemService{
	private AdminDao adminDao;
	private RefereeDao refereeDao;
	
	@Autowired
	public SystemServiceImp(AdminDao adminDao, RefereeDao refereeDao) {
		this.adminDao = adminDao;
		this.refereeDao = refereeDao;
	}
	
	public Person findPersonByUid( String uid) throws DataAccessException {
		if(uid.contains("admin")){
			return adminDao.getAdmin();
		}
		else if(uid.contains("referee")){
			return refereeDao.getRefereeByUid(uid);
		}
		else
			return null;
	}
	
	public void changePersonPassword(String uid, String password) throws DataAccessException {
		if(uid.contains("admin")){
			adminDao.changePassword(password);
		}
		else if(uid.contains("referee")){
			refereeDao.changePassword(uid, password);
		}
		else
			return;
	}
}