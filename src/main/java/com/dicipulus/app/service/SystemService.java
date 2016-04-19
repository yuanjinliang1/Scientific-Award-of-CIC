package com.dicipulus.app.service;

import org.springframework.dao.DataAccessException;
import com.dicipulus.app.model.Person;

public interface SystemService {
	
	Person findPersonByUid(String uid) throws DataAccessException;
	
	void changePersonPassword(String uid, String password) throws DataAccessException;
	
}