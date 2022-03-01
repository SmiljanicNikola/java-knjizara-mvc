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

import com.example.KnjizaraProjekatPOPRAVKA.dao.KnjigaDAO;
import com.example.KnjizaraProjekatPOPRAVKA.dao.KomentarDAO;
import com.example.KnjizaraProjekatPOPRAVKA.dao.KorisnikDAO;
import com.example.KnjizaraProjekatPOPRAVKA.model.Knjiga;
import com.example.KnjizaraProjekatPOPRAVKA.model.Komentar;
import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;


@Repository
public class KomentarDAOImpl implements KomentarDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	@Autowired
	private KorisnikDAO korisnikDAO;
	
	@Autowired
	private KnjigaDAO knjigaDAO;

	
	private class KomentariRowMapper implements RowMapper<Komentar>{

		@Override
		public Komentar mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index = 1;
			int id=rs.getInt(index++);
			String tekstKomentara = rs.getString(index++);
			int ocena = rs.getInt(index++);
			LocalDate datumPostavljanja = rs.getDate(index++).toLocalDate();
			String autorOznaka = rs.getString(index++);
			Korisnik autor = korisnikDAO.findOne(autorOznaka);
			int knjigaId = rs.getInt(index++);
			Knjiga knjiga = knjigaDAO.findOne(knjigaId);
			String status = rs.getString(index++);
				
			Komentar komentar = new Komentar(id, tekstKomentara, ocena, datumPostavljanja, autor, knjiga, status);
			return komentar;
		}	
		
	}
	
	@Override
	public Komentar findOne(int id) {
		try {
			String sql = "SELECT * FROM komentari where id = ?";
			return jdbcTemplate.queryForObject(sql, new KomentariRowMapper(), id);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public List<Komentar> findAll() {
		String sql = "SELECT id, tekstKomentara, ocena, datumPostavljanja, autorOznaka, knjigaId, status from komentari";
		return jdbcTemplate.query(sql, new KomentariRowMapper());
	}

	@Override
	public List<Komentar> find(int id, String tekstKomentara, int ocena, LocalDate datumPostavljanja, String autorOznaka,
			int knjigaId, String status) {
		return null;
	}

	@Override
	public List<Komentar> findVerzija2(HashMap<String, Object> mapaArgumenata) {
		return null;
	}

	@Override
	public void save(Komentar komentar) {
		String sql = "INSERT INTO komentari(id,tekstKomentara, ocena, datumPostavljanja, autorOznaka, knjigaId, status) VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, komentar.getId(), komentar.getTekstKomentara(), komentar.getOcena(), komentar.getDatumPostavljanja() , komentar.getAutor().getKorisnickoIme(), komentar.getKnjiga().getId(), komentar.getStatus());	
	}
		

	@Override
	public void update(Komentar komentar) {
		String sql = "UPDATE komentari SET tekstKomentara=?, ocena=?, datumPostavljanja=?, autorOznaka=?, knjigaId=?, status=? WHERE id=?";
		jdbcTemplate.update(sql, komentar.getTekstKomentara(), komentar.getOcena(), komentar.getDatumPostavljanja(), komentar.getAutor().getKorisnickoIme(), komentar.getKnjiga().getId(), komentar.getStatus(), komentar.getId());
		
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Komentar> find(int knjigaId) {
		String sql = "SELECT id, tekstKomentara, ocena, datumPostavljanja, autorOznaka, knjigaId, status from komentari where knjigaId=?";
		return jdbcTemplate.query(sql, new KomentariRowMapper(), knjigaId);

	}

}
