package com.example.KnjizaraProjekatPOPRAVKA.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.KnjizaraProjekatPOPRAVKA.dao.KorisnikDAO;
import com.example.KnjizaraProjekatPOPRAVKA.dao.LoyaltyKarticaDAO;
import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;
import com.example.KnjizaraProjekatPOPRAVKA.model.ListaZelja;
import com.example.KnjizaraProjekatPOPRAVKA.model.LoyaltyKartica;


@Repository
public class LoyaltyKarticaDAOImpl implements LoyaltyKarticaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	private class LoyaltyKarticeRowMapper implements RowMapper<LoyaltyKartica>{

		@Override
		public LoyaltyKartica mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			int id=rs.getInt(index++);
			String popust = rs.getString(index++);
			int brPoena = rs.getInt(index++);
			String vlasnikOznaka = rs.getString(index++);
			Korisnik vlasnik = korisnikDAO.findOne(vlasnikOznaka);
			String status = rs.getString(index++);
			
			
			LoyaltyKartica loyaltyKartica = new LoyaltyKartica(id, popust, brPoena, vlasnik, status);
			return loyaltyKartica;
			
		}
		
		
		
	}

	@Override
	public LoyaltyKartica findOne(int id) {
		try {
			String sql = "SELECT * FROM loyaltykartice where id = ?";
			return jdbcTemplate.queryForObject(sql, new LoyaltyKarticeRowMapper(), id);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	
	

	@Override
	public List<LoyaltyKartica> findAll() {
		String sql = "SELECT id, popust, brPoena, vlasnikOznaka, status from loyaltykartice";
		return jdbcTemplate.query(sql, new LoyaltyKarticeRowMapper());
	}

	@Override
	public List<LoyaltyKartica> find(int id, String popust, int brPoena, String vlasnikOznaka, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LoyaltyKartica> findVerzija2(HashMap<String, Object> mapaArgumenata) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(LoyaltyKartica loyaltyKartica) {
		String sql = "INSERT INTO loyaltykartice(popust, brPoena, vlasnikOznaka, status) VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, loyaltyKartica.getPopust(), loyaltyKartica.getBrPoena(), loyaltyKartica.getVlasnik().getKorisnickoIme(), loyaltyKartica.getStatus());
		
	}

	@Override
	public void update(LoyaltyKartica loyaltyKartica) {
		String sql = "UPDATE loyaltykartice SET popust=?, brPoena=?, vlasnikOznaka=?, status=? WHERE id=?";
		jdbcTemplate.update(sql, loyaltyKartica.getPopust(), loyaltyKartica.getBrPoena(), loyaltyKartica.getVlasnik().getKorisnickoIme(), loyaltyKartica.getStatus(), loyaltyKartica.getId());
		
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public LoyaltyKartica findOne(String vlasnikOznaka) {
		try {
			String sql = "SELECT * FROM loyaltykartice where vlasnikOznaka = ?";
			return jdbcTemplate.queryForObject(sql, new LoyaltyKarticeRowMapper(), vlasnikOznaka);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

}
