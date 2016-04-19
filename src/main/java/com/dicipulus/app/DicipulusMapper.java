package com.dicipulus.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class DicipulusMapper implements RowMapper<Dicipulus> {
	public Dicipulus mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dicipulus student =new Dicipulus();
		student.setId(rs.getInt("id"));
		student.setName(rs.getString("name"));
		student.setPhone(rs.getString("phone"));
		return student;
	}
}