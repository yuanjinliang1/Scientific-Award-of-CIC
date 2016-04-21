package com.dicipulus.app.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dicipulus.app.model.*;

public class RefereeMapper implements RowMapper<Referee> {
	public Referee mapRow(ResultSet rs, int rowNum) throws SQLException{
		Referee referee= new Referee();
		referee.setUid(rs.getString("uid"));
		referee.setPassword(rs.getString("password"));
		referee.setName(rs.getString("name"));
		referee.setRole(rs.getString("role"));
		referee.setAddress(rs.getString("address"));
		referee.setCategory(rs.getString("category"));
		referee.setTelephone(rs.getString("telephone"));
		return referee;
	}
}