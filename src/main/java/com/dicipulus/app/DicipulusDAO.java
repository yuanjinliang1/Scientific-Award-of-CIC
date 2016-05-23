package com.dicipulus.app;

import java.util.List;

import javax.sql.DataSource;

public interface DicipulusDAO {
	public void setDataSource (DataSource ds);
	
	public void create(String name, Integer id);
	
	public void create(String name, Integer id, String phone);
	
	public  Dicipulus getDicipulus(Integer id);
	
	public List<Dicipulus> listDicipulus();
	
	public void delete(Integer id);
	
	public void update(Integer id, Integer name);
}