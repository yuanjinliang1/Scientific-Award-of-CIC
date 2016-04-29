package com.dicipulus.app.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dicipulus.app.model.Admin;

public class AdminMapper implements RowMapper<Admin> {
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException{
		Admin admin= new Admin();
		admin.setUid(rs.getString("uid"));
		admin.setPassword(rs.getString("password"));
		admin.setName(rs.getString("name"));
		admin.setRole(rs.getString("role"));
		return admin;
	}
}