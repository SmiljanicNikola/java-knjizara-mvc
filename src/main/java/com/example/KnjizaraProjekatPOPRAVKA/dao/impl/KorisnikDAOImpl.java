package com.example.KnjizaraProjekatPOPRAVKA.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.KnjizaraProjekatPOPRAVKA.dao.KorisnikDAO;
import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;


@Repository
public class KorisnikDAOImpl implements KorisnikDAO {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private class KorisnikRowMapper implements RowMapper<Korisnik>{

		@Override
		public Korisnik mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index=1;
			String ime = rs.getString(index++);
			String prezime = rs.getString(index++);
			String email = rs.getString(index++);
			String adresa = rs.getString(index++);
			String telefon = rs.getString(index++);
			String korisnickoIme = rs.getString(index++);
			String lozinka = rs.getString(index++);
			LocalDate datumRodjenja = rs.getDate(index++).toLocalDate();
			LocalDateTime datumRegistracije = rs.getTimestamp(index++).toLocalDateTime();
			String uloga = rs.getString(index++);
			Boolean blokiran = rs.getBoolean(index++);

			Korisnik korisnik = new Korisnik(ime, prezime, email, adresa, telefon, korisnickoIme, lozinka,datumRodjenja, datumRegistracije, uloga, false, blokiran);
			return korisnik;
		}
		
		
	}
	
	@Override
	public Korisnik findOne(String korisnickoIme) {
		try {
			String sql = "SELECT ime,prezime,email,adresa,telefon,korisnickoIme,lozinka,datumRodjenja,datumRegistracije,uloga, blokiran blokiran FROM korisnici where korisnickoIme = ?";
			return jdbcTemplate.queryForObject(sql, new KorisnikRowMapper(), korisnickoIme);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
			
	}

	@Override
	public Korisnik findOne(String korisnickoIme, String lozinka) {
		try {
			String sql = "SELECT ime,prezime,email,adresa,telefon,korisnickoIme, lozinka, datumRodjenja,datumRegistracije,uloga,blokiran FROM korisnici where korisnickoIme = ? AND lozinka = ?";
			return jdbcTemplate.queryForObject(sql, new KorisnikRowMapper(), korisnickoIme, lozinka);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public List<Korisnik> findAll() {
		String sql = "SELECT ime, prezime, email, adresa, telefon, korisnickoIme,lozinka, datumRodjenja, datumRegistracije, uloga, blokiran from korisnici";
		return jdbcTemplate.query(sql, new KorisnikRowMapper());
	}

	@Override
	public List<Korisnik> find(String ime, String prezime, String email, String adresa, String telefon,
			String korisnickoIme, String lozinka, LocalDate datumRodjenja, LocalDateTime datumRegistracije, String uloga) {
		
				ArrayList <Object> listaArgumenata = new ArrayList<Object>();
				
				String sql = "Select ime, prezime, email, adresa, telefon, korisnickoIme, lozinka, datumRodjenja, datumRegistracije, uloga from korisnici";
				
				StringBuffer whereSql = new StringBuffer(" WHERE ");
				boolean imaArgumenata = false;
				
				if(ime != null) {
					ime = "%" + ime + "%";
					if(imaArgumenata)
						whereSql.append(" AND ");
					whereSql.append("ime like ?");
					imaArgumenata = true;
					listaArgumenata.add(ime);
				}
				if(prezime != null) {
					prezime = "%" + prezime + "%";
					if(imaArgumenata)
						whereSql.append(" AND ");
					whereSql.append("prezime like ?");
					imaArgumenata = true;
					listaArgumenata.add(prezime);
				}
				if(email != null) {
					email = "%" + email + "%";
					if(imaArgumenata)
						whereSql.append(" AND ");
					whereSql.append("email like ?");
					imaArgumenata = true;
					listaArgumenata.add(email);
				}
				if(adresa != null) {
					adresa = "%" + adresa + "%";
					if(imaArgumenata)
						whereSql.append(" AND ");
					whereSql.append("adresa like ?");
					imaArgumenata = true;
					listaArgumenata.add(adresa);
				}
				if(telefon != null) {
					telefon = "%" + telefon + "%";
					if(imaArgumenata)
						whereSql.append(" AND ");
					whereSql.append("telefon like ?");
					imaArgumenata = true;
					listaArgumenata.add(telefon);
				}
				if(korisnickoIme!=null) {
					korisnickoIme = "%" + korisnickoIme + "%";
					if(imaArgumenata)
						whereSql.append(" AND ");
					whereSql.append("korisnickoIme LIKE ?");
					imaArgumenata = true;
					listaArgumenata.add(korisnickoIme);
				}
				if(lozinka!=null) {
					lozinka = "%" + lozinka + "%";
					if(imaArgumenata)
						whereSql.append(" AND ");
					whereSql.append("lozinka LIKE ?");
					imaArgumenata = true;
					listaArgumenata.add(lozinka);
				}
				if(datumRodjenja!=null) {
					if(imaArgumenata)
						whereSql.append(" AND ");
					whereSql.append("datumRodjenja >= ?");
					imaArgumenata = true;
					listaArgumenata.add(datumRodjenja);
				}
				if(datumRegistracije!=null) {
					if(imaArgumenata)
						whereSql.append(" AND ");
					whereSql.append("datumRegistracije >= ?");
					imaArgumenata = true;
					listaArgumenata.add(datumRegistracije);
				}
				if(uloga!=null) {
					uloga = "%" + uloga + "%";
					if(imaArgumenata)
						whereSql.append(" AND ");
					whereSql.append("uloga LIKE ?");
					imaArgumenata = true;
					listaArgumenata.add(uloga);
				}
				if(imaArgumenata)
					sql = sql + whereSql.toString() + "Order by korisnickoIme";
				else 
					sql = sql + "ORDER BY korisnickoIme";
				System.out.println(sql);
				
				return jdbcTemplate.query(sql, listaArgumenata.toArray(), new KorisnikRowMapper());
				
	}

	@Override
	public void save(Korisnik korisnik) {
		String sql = "INSERT INTO korisnici(ime,prezime,email,adresa,telefon,korisnickoIme,lozinka,datumRodjenja,datumRegistracije,uloga, blokiran) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, korisnik.getIme(), korisnik.getPrezime(),korisnik.getEmail(),korisnik.getAdresa(),korisnik.getTelefon(),
				korisnik.getKorisnickoIme(),korisnik.getLozinka(),korisnik.getDatumRodjenja(),korisnik.getDatumRegistracije(),korisnik.getUloga(), korisnik.isBlokiran());
				
	}

	@Override
	public void update(Korisnik korisnik) {
		String sql = "UPDATE korisnici SET adresa=?, uloga=?, blokiran=? WHERE korisnickoIme=?";
		jdbcTemplate.update(sql,korisnik.getAdresa(), korisnik.getUloga(), korisnik.isBlokiran(), korisnik.getKorisnickoIme());
		
	}

	@Override
	public void delete(String korisnickoIme) {
		// TODO Auto-generated method stub
		
	}

}
