package com.example.KnjizaraProjekatPOPRAVKA.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.example.KnjizaraProjekatPOPRAVKA.dao.KnjigaDAO;
import com.example.KnjizaraProjekatPOPRAVKA.dao.KorisnikDAO;
import com.example.KnjizaraProjekatPOPRAVKA.dao.KupovinaDAO;
import com.example.KnjizaraProjekatPOPRAVKA.model.Knjiga;
import com.example.KnjizaraProjekatPOPRAVKA.model.Korisnik;
import com.example.KnjizaraProjekatPOPRAVKA.model.Kupovina;


@Repository
public class KupovinaDAOImpl implements KupovinaDAO  {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private KnjigaDAO knjigaDAO;
	
	@Autowired
	private KorisnikDAO korisnikDAO;
	

	
	
	private class KupovineRowMapper implements RowMapper<Kupovina>{

		private Map<Integer, Kupovina> kupovine = new LinkedHashMap<>();
		
		@Override
		public Kupovina mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index=1;
			int id = rs.getInt(index++);
			Integer knjigaId=rs.getInt(index++);
			Knjiga knjiga = knjigaDAO.findOne(knjigaId);
			Float ukupnaCena= rs.getFloat(index++);
			LocalDate datumKupovine = rs.getDate(index++).toLocalDate();
			String musterijaOznaka = rs.getString(index++);
			Korisnik musterija = korisnikDAO.findOne(musterijaOznaka);
			Integer brojKupljenihKnjiga = rs.getInt(index++);

			
			Kupovina kupovina = kupovine.get(id);
			kupovina = new Kupovina(id,knjiga, ukupnaCena, datumKupovine, musterija, brojKupljenihKnjiga);
				kupovine.put(kupovina.getId(), kupovina); // dodavanje u kolekciju
			return kupovina;
				
				}
			
		}
	
	
	
	
	
	@Override
	public Kupovina findOne(int id) {
		try {
			String sql = "SELECT * FROM kupovine where id = ?";
			return jdbcTemplate.queryForObject(sql, new KupovineRowMapper(), id);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public List<Kupovina> findAll() {
		String sql = "SELECT id, knjigaId, ukupnaCena, datumKupovine, musterijaOznaka, brojKupljenihKnjiga from kupovine";
		return jdbcTemplate.query(sql, new KupovineRowMapper());
	}

	@Override
	public List<Kupovina> find(int id, String musterijaOznaka) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Kupovina> find(int knjigaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Kupovina> find(String musterijaOznaka) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Kupovina kupovina) {
		// TODO Auto-generated method stub
		
		/*PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO kupovine(id, knjigeId, ukupnaCena, datumKupovine, musterija, brojKupljenihKnjiga) VALUES (?,?,?,?,?,?)";
				jdbcTemplate.update(sql, knjiga.getId(), knjiga.getNaziv(), knjiga.getIsbn(), knjiga.getIzdavackaKuca(), knjiga.getAutor(), knjiga.getGodinaIzdavanja(),
						knjiga.getKratakOpis(),knjiga.getCena(),knjiga.getBrojStranica(),knjiga.getTipPoveza(), knjiga.getPismo(), knjiga.getJezik(), knjiga.getProsecnaOcena());
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				
				preparedStatement.setInt(index++, kupovina.getId());
				preparedStatement.setFloat(index++,kupovina.getUkupnaCena());
				preparedStatement.setDate(index++, java.sql.Date.valueOf(kupovina.getDatumKupovine()));
				preparedStatement.setString(index++,kupovina.getMusterija().getKorisnickoIme());
				preparedStatement.setInt(index++,kupovina.getBrojKupljenihKnjiga());


				return preparedStatement;*/
		String sql = "INSERT INTO kupovine(id,knjigaId, ukupnaCena, datumKupovine, musterijaOznaka, brojKupljenihKnjiga) VALUES (?,?,?,?,?,?)";
		jdbcTemplate.update(sql, kupovina.getId(), kupovina.getKnjiga().getId(), kupovina.getUkupnaCena(), kupovina.getDatumKupovine() , kupovina.getMusterija().getKorisnickoIme(), kupovina.getBrojKupljenihKnjiga());
			}

	
		
		/*GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		if (uspeh) {
			String sql = "INSERT INTO kupovinaknjiga (kupovinaid, knjigeid) VALUES (?, ?)";
			for (Knjiga itKnjiga: kupovina.getKupljeneKnjige()) {	
				uspeh = uspeh && jdbcTemplate.update(sql, keyHolder.getKey(), itKnjiga.getId()) == 1;
			}
		}
		return uspeh?1:0;
		//return;
		
		
	}*/


	@Override
	public int update(Kupovina kupovina) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Kupovina findOne(LocalDate datumKupovine) {
		try {
			String sql = "SELECT * FROM kupovine where datumKupovine = ?";
			return jdbcTemplate.queryForObject(sql, new KupovineRowMapper(), datumKupovine);
		} catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public List<Kupovina> find(HashMap<String, Object> mapaArgumenata) {
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT id,knjigaId,ukupnaCena,datumKupovine,musterijaOznaka,brojKupljenihKnjiga from kupovine"; 
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
				
		if(mapaArgumenata.containsKey("datumKupovineOd")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("datumKupovine >= ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("datumKupovineOd"));
		}
		
		if(mapaArgumenata.containsKey("datumKupovineDo")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("datumKupovine <= ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("datumKupovineDo"));
			
			
		}
		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY id";
		else
			sql=sql + " ORDER BY id";
		System.out.println(sql);
		
		return jdbcTemplate.query(sql, new KupovineRowMapper(), listaArgumenata.toArray());
		
	}
	
}

	


