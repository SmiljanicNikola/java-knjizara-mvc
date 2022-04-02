package com.example.KnjizaraProjekatPOPRAVKA.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.KnjizaraProjekatPOPRAVKA.dao.KnjigaDAO;
import com.example.KnjizaraProjekatPOPRAVKA.dao.KorisnikDAO;
import com.example.KnjizaraProjekatPOPRAVKA.dao.ListaZeljaDAO;
import com.example.KnjizaraProjekatPOPRAVKA.model.Knjiga;
import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;
import com.example.KnjizaraProjekatPOPRAVKA.model.ListaZelja;


@Repository
public class ListaZeljaDAOImpl implements ListaZeljaDAO {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KnjigaDAO knjigaDAO;
	
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	private class ListaZeljaRowMapper implements RowMapper<ListaZelja>{

		@Override
		public ListaZelja mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			int id=rs.getInt(index++);
			int knjigaId = rs.getInt(index++);
			Knjiga knjiga = knjigaDAO.findOne(knjigaId);
			String vlasnikListeOznaka = rs.getString(index++);
			Korisnik vlasnikListe = korisnikDAO.findOne(vlasnikListeOznaka);
				
			ListaZelja listaZelja = new ListaZelja(id, knjiga, vlasnikListe);
			return listaZelja;
			
		}
	}

	@Override
	public ListaZelja findOne(int id) {
		try {
			String sql = "SELECT * FROM listezelja where id = ?";
			return jdbcTemplate.queryForObject(sql, new ListaZeljaRowMapper(), id);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public List<ListaZelja> findAll() {
		String sql = "SELECT id, knjigaId, vlasnikListeOznaka from listezelja";
		return jdbcTemplate.query(sql, new ListaZeljaRowMapper());

	}

	@Override
	public List<ListaZelja> find(int id, int knjigaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ListaZelja> find(int knjigaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ListaZelja> find(String vlasnikListeOznaka) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ListaZelja listaZelja) {
		String sql = "INSERT INTO listezelja(id, knjigaId, vlasnikListeOznaka) VALUES (?,?,?)";
		jdbcTemplate.update(sql, listaZelja.getId(), listaZelja.getKnjiga().getId(), listaZelja.getVlasnikListe().getKorisnickoIme());	
		
	}

	@Override
	public int update(ListaZelja listaZelja) {
		String sql = "UPDATE projekcije SET id = 0, knjigaId = 0, vlasnikListeOznaka=0, WHERE id  = ?";
		return jdbcTemplate.update(sql, listaZelja.getId(), listaZelja.getKnjiga().getId(), listaZelja.getVlasnikListe().getKorisnickoIme(), listaZelja.getId());
		
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM listezelja WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public ListaZelja findOne(String vlasnikOznaka) {
		try {
			String sql = "SELECT * FROM listezelja where vlasnikOznaka = ?";
			return jdbcTemplate.queryForObject(sql, new ListaZeljaRowMapper(), vlasnikOznaka);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}
	

}
