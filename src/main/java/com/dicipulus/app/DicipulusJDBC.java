package com.dicipulus.app;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class DicipulusJDBC implements DicipulusDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplateObject= new JdbcTemplate(dataSource);
	}
	
	public void create(String name, Integer id){
		String sql="insert into Student (name, id) values (?,?)";
		
		jdbcTemplateObject.update(sql, name, id);
		System.out.println(sql);
	}
	
	public void create(String name, Integer id, String phone){
		String sql="insert into Student (name, id, phone) values (?,?,?)";
		
		jdbcTemplateObject.update(sql, name, id, phone);
		System.out.println(sql);
	}
	
	public Dicipulus getDicipulus (Integer id){
		String sql="select * from Student where id =?";
		
		Dicipulus student= jdbcTemplateObject.queryForObject(sql, new Object[]{id},
				new DicipulusMapper());
		System.out.println(sql);
		return student;
	}
	
	public List<Dicipulus> listDicipulus(){
		String sql="select * from Student";
		
		List<Dicipulus> students =jdbcTemplateObject.query(sql, new DicipulusMapper());
		System.out.println(sql);
		return students;
	}
	
	public void delete(Integer id){
		String sql="delete from Student where id = ?";
		
		jdbcTemplateObject.update(sql, id);
		System.out.println(sql);
	}
	
	public void update(Integer id, Integer name) {
		String sql="update Student set name= ? where id =?";
		
		jdbcTemplateObject.update(sql, name, id);
		System.out.println(sql);
	}
}