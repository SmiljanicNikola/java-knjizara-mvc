package com.example.KnjizaraProjekatPOPRAVKA.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.KnjizaraProjekatPOPRAVKA.dao.ZanrDAO;
import com.example.KnjizaraProjekatPOPRAVKA.model.Zanr;

@Repository
@Primary
public class ZanrDAOImpl implements ZanrDAO {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class ZanrRowMapper implements RowMapper<Zanr> {

		@Override
		public Zanr mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			Integer id = rs.getInt(index++);
			String ime = rs.getString(index++);
			String opis = rs.getString(index++);

			Zanr zanr = new Zanr(id, ime, opis);
			return zanr;
		}

	}
	
	@Override
	public Zanr findOne(Integer id) {
		String sql = "SELECT id, ime, opis FROM zanrovi WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new ZanrRowMapper(), id);
	}

	@Override
	public List<Zanr> findAll() {
		String sql = "SELECT id, ime, opis FROM zanrovi";
		return jdbcTemplate.query(sql, new ZanrRowMapper());
	}

	@Override
	public List<Zanr> find(String ime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Zanr zanr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] save(ArrayList<Zanr> zanrovi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Zanr zanr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
