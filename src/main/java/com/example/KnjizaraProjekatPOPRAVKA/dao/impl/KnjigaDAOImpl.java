package com.example.KnjizaraProjekatPOPRAVKA.dao.impl;
import java.sql.Date;
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
import org.springframework.transaction.annotation.Transactional;

import com.example.KnjizaraProjekatPOPRAVKA.dao.KnjigaDAO;
import com.example.KnjizaraProjekatPOPRAVKA.dao.ZanrDAO;
import com.example.KnjizaraProjekatPOPRAVKA.model.Knjiga;
import com.example.KnjizaraProjekatPOPRAVKA.model.Zanr;


@Repository
public class KnjigaDAOImpl implements KnjigaDAO {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private ZanrDAO zanrDAO; 
	
	
	/*private class KorisnikRowMapper implements RowMapper<Knjiga>{

		@Override
		public Knjiga mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index=1;
			int id = rs.getInt(index++);
			String naziv = rs.getString(index++);
			long isbn = rs.getLong(index++);
			String izdavackaKuca = rs.getString(index++);
			String autor = rs.getString(index++);
			LocalDate godinaIzdavanja = rs.getDate(index++).toLocalDate();
			String kratakOpis = rs.getString(index++);
			Float cena = rs.getFloat(index++);
			int brojStranica = rs.getInt(index++);
			String tipPoveza = rs.getString(index++);
			String pismo = rs.getString(index++);
			String jezik = rs.getString(index++);
			float prosecnaOcena = rs.getFloat(index++);

			Knjiga knjiga = new Knjiga(id, naziv, isbn, izdavackaKuca, autor, godinaIzdavanja, kratakOpis, cena, brojStranica, tipPoveza, pismo, jezik, prosecnaOcena);
			return knjiga;
		}
		
	}*/
	
	private class KnjigaRowCallBackHandler implements RowCallbackHandler {

		private Map<Integer, Knjiga> knjige = new LinkedHashMap<>();
		
		@Override
		public void processRow(ResultSet rs) throws SQLException {
			int index=1;
			int id = rs.getInt(index++);			
			String naziv = rs.getString(index++);
			long isbn = rs.getLong(index++);
			String izdavackaKuca = rs.getString(index++);
			String autor = rs.getString(index++);
			LocalDate godinaIzdavanja = rs.getDate(index++).toLocalDate();
			String kratakOpis = rs.getString(index++);
			Float cena = rs.getFloat(index++);
			int brojStranica = rs.getInt(index++);
			String tipPoveza = rs.getString(index++);
			String pismo = rs.getString(index++);
			String jezik = rs.getString(index++);
			float prosecnaOcena = rs.getFloat(index++);
			int brojPrimeraka = rs.getInt(index++);
			Integer zanrId = rs.getInt(index++);
			
			
			Zanr zanr = zanrDAO.findOne(zanrId);
			
			Knjiga knjiga = knjige.get(id);
			if (knjiga == null) {
				knjiga = new Knjiga(id, naziv, isbn, izdavackaKuca, autor, godinaIzdavanja, kratakOpis, cena, brojStranica, tipPoveza,pismo, jezik, prosecnaOcena, brojPrimeraka);
				knjige.put(knjiga.getId(), knjiga); // dodavanje u kolekciju
				knjiga.getZanrovi().add(zanr);
			}
			
		}
	
		public List<Knjiga> getKnjige() {
			return new ArrayList<>(knjige.values());
		}

	}
	

	@Override
	public Knjiga findOne(int id) {
			String sql = "SELECT * FROM knjige where id = ?";
			
			KnjigaRowCallBackHandler rowCallbackHandler = new KnjigaRowCallBackHandler();
			jdbcTemplate.query(sql, rowCallbackHandler, id);

			return rowCallbackHandler.getKnjige().get(0);
		}
	

	@Override
	public Knjiga findOne(long isbn) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public List<Knjiga> findAll() {
		String sql = "SELECT k.id, k.naziv, k.isbn, k.izdavackaKuca, k.autor, k.godinaIzdavanja, k.kratakOpis, k.cena, k.brojStranica, k.tipPoveza, k.pismo, k.jezik, k.prosecnaOcena, k.brojPrimeraka,z.id, z.ime from knjige k " +
					"LEFT JOIN knjigazanr kz ON kz.knjigaid = k.id " +
					"LEFT JOIN zanrovi z ON kz.zanrid = z.id " +
					"ORDER BY k.id ";
		//String sql = "SELECT k.id, k.naziv, k.isbn, k.izdavackaKuca, k.autor, k.godinaIzdavanja, k.kratakOpis, k.cena, k.brojStranica, k.tipPoveza, k.pismo, k.jezik, k.prosecnaOcena, k.zanrId from knjige k ";
		//return jdbcTemplate.query(sql, new KorisnikRowMapper());
		KnjigaRowCallBackHandler rowCallbackHandler = new KnjigaRowCallBackHandler();
		jdbcTemplate.query(sql, rowCallbackHandler);

		return rowCallbackHandler.getKnjige();
	}
	
	
	private class KnjigaRowMapper implements RowMapper<Knjiga> {

		@Override
		public Knjiga mapRow(ResultSet rs, int rowNum) throws SQLException {
			int index=1;
			int id = rs.getInt(index++);			
			String naziv = rs.getString(index++);
			long isbn = rs.getLong(index++);
			String izdavackaKuca = rs.getString(index++);
			String autor = rs.getString(index++);
			LocalDate godinaIzdavanja = rs.getDate(index++).toLocalDate();
			String kratakOpis = rs.getString(index++);
			Float cena = rs.getFloat(index++);
			int brojStranica = rs.getInt(index++);
			String tipPoveza = rs.getString(index++);
			String pismo = rs.getString(index++);
			String jezik = rs.getString(index++);
			float prosecnaOcena = rs.getFloat(index++);
			int brojPrimeraka=rs.getInt(index++);
			

			Knjiga knjiga = new Knjiga(id, naziv, isbn, izdavackaKuca, autor, godinaIzdavanja, kratakOpis, cena, brojStranica, tipPoveza,pismo, jezik, prosecnaOcena,brojPrimeraka);
			return knjiga;
		}

	}

	@Transactional
	@Override
	public int save(Knjiga knjiga) {		
		/*String sql = "INSERT INTO knjige(id,naziv,isbn,izdavackaKuca,autor,godinaIzdavanja,kratakOpis,cena,brojStranica,tipPoveza,pismo,jezik,prosecnaOcena, ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, knjiga.getId(), knjiga.getNaziv(), knjiga.getIsbn(), knjiga.getIzdavackaKuca(), knjiga.getAutor(), knjiga.getGodinaIzdavanja(),
				knjiga.getKratakOpis(),knjiga.getCena(),knjiga.getBrojStranica(),knjiga.getTipPoveza(), knjiga.getPismo(), knjiga.getJezik(), knjiga.getProsecnaOcena());*/
		
		PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String sql = "INSERT INTO knjige(id,naziv,isbn,izdavackaKuca,autor,godinaIzdavanja,kratakOpis,cena,brojStranica,tipPoveza,pismo,jezik,prosecnaOcena) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
				/*jdbcTemplate.update(sql, knjiga.getId(), knjiga.getNaziv(), knjiga.getIsbn(), knjiga.getIzdavackaKuca(), knjiga.getAutor(), knjiga.getGodinaIzdavanja(),
						knjiga.getKratakOpis(),knjiga.getCena(),knjiga.getBrojStranica(),knjiga.getTipPoveza(), knjiga.getPismo(), knjiga.getJezik(), knjiga.getProsecnaOcena());*/
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				
				preparedStatement.setInt(index++, knjiga.getId());
				preparedStatement.setString(index++,knjiga.getNaziv());
				preparedStatement.setLong(index++, knjiga.getIsbn());
				preparedStatement.setString(index++,knjiga.getIzdavackaKuca());
				preparedStatement.setString(index++,knjiga.getAutor());
				preparedStatement.setDate(index++, java.sql.Date.valueOf(knjiga.getGodinaIzdavanja()));
				preparedStatement.setString(index++,knjiga.getKratakOpis());
				preparedStatement.setFloat(index++,knjiga.getCena());
				preparedStatement.setInt(index++,knjiga.getBrojStranica());
				preparedStatement.setString(index++,knjiga.getTipPoveza());
				preparedStatement.setString(index++,knjiga.getPismo());
				preparedStatement.setString(index++,knjiga.getJezik());
				preparedStatement.setFloat(index++,knjiga.getProsecnaOcena());
				
				return preparedStatement;
			}

		};
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		boolean uspeh = jdbcTemplate.update(preparedStatementCreator, keyHolder) == 1;
		if (uspeh) {
			String sql = "INSERT INTO knjigazanr (knjigaid, zanrid) VALUES (?, ?)";
			for (Zanr itZanr: knjiga.getZanrovi()) {	
				uspeh = uspeh && jdbcTemplate.update(sql, keyHolder.getKey(), itZanr.getId()) == 1;
			}
		}
		return uspeh?1:0;
		
	}

	@Override
	public void update(Knjiga knjiga) {
		String sql = "UPDATE knjige SET naziv=?, isbn=?, izdavackaKuca=?, autor=?, godinaIzdavanja=?, kratakOpis=?, cena=?, brojStranica=?, tipPoveza=?, pismo=?, jezik=?, prosecnaOcena=? WHERE id=?";
		jdbcTemplate.update(sql, knjiga.getNaziv(), knjiga.getIsbn(), knjiga.getIzdavackaKuca(), knjiga.getAutor(), knjiga.getGodinaIzdavanja(), knjiga.getKratakOpis(), knjiga.getCena(), knjiga.getBrojStranica(), knjiga.getTipPoveza(), knjiga.getPismo(), knjiga.getJezik(), knjiga.getProsecnaOcena(), knjiga.getId());	
		 
	}
	
	@Override
	public void update2(Knjiga knjiga) {
		String sql = "UPDATE knjige SET brojPrimeraka=? WHERE id=?";
		jdbcTemplate.update(sql, knjiga.getBrojPrimeraka(), knjiga.getId());	
		 
	}


	@Override
	public void delete(int id) {
		String sql = "DELETE FROM knjige WHERE id = ?";
		jdbcTemplate.update(sql, id);
		
	}

	@Override
	public List<Knjiga> findVerzija2(HashMap<String, Object> mapaArgumenata) {
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT id, naziv, isbn, izdavackaKuca, autor, godinaIzdavanja, kratakOpis, cena, brojStranica, tipPoveza, pismo, jezik, prosecnaOcena from knjige";
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		
		if(mapaArgumenata.containsKey("naziv")) {
			String naziv = "%" + mapaArgumenata.get("naziv") + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("naziv LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("naziv"));
		}
		
		if(mapaArgumenata.containsKey("isbn")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("isbn = ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("isbn"));
		}
		
		if(mapaArgumenata.containsKey("izdavackaKuca")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("izdavackaKuca like ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("izdavackaKuca"));
		}
		
		if(mapaArgumenata.containsKey("autor")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("autor like ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("autor"));
		}
		
		if(mapaArgumenata.containsKey("kratakOpis")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("kratakOpis like ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("kratakOpis"));
		}
		
		if(mapaArgumenata.containsKey("cena")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("cena = ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("cena"));
		}
		
		if(mapaArgumenata.containsKey("brojStranica")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("brojStranica = ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("brojStranica"));
		}
		
		if(mapaArgumenata.containsKey("tipPoveza")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("tipPoveza like ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("tipPoveza"));
		}
		
		if(mapaArgumenata.containsKey("pismo")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("pismo like ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("pismo"));
		}
		
		if(mapaArgumenata.containsKey("jezik")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("jezik like ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("jezik"));
		}
		
		if(mapaArgumenata.containsKey("prosecnaOcena")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("prosecnaOcena = ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("prosecnaOcena"));
		}
		
		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY id";
		else
			sql=sql + " ORDER BY id";
		System.out.println(sql);
		
		return jdbcTemplate.query(sql, new KnjigaRowMapper(), listaArgumenata.toArray());
	}

	@Override
	public Knjiga findOne(String naziv) {
		
			String sql = "SELECT * FROM knjige where naziv = ?";
			KnjigaRowCallBackHandler rowCallbackHandler = new KnjigaRowCallBackHandler();
			jdbcTemplate.query(sql, rowCallbackHandler, naziv);

			return rowCallbackHandler.getKnjige().get(0);		
		
	}

	
	@Override
	public List<Knjiga> find(String naziv, Long isbn, String izdavackaKuca, String autor, LocalDate godinaIzdavanja,
			String kratakOpis, float cena, int brojStranica, String tipPoveza, String pismo, String jezik,
			float prosecnaOcena) {
			
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT id, naziv, isbn, izdavackaKuca, autor, godinaIzdavanja, kratakOpis, cena, brojStranica, tipPoveza, pismo, jezik, prosecnaOcena from knjige";
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		

		if(naziv!=null) {
			naziv = "%" + naziv + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("naziv LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(naziv);
		}
		
		if(cena != 0) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("cena >= ?");
			imaArgumenata = true;
			listaArgumenata.add(cena);
		}
		if(isbn != 0) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("isbn = ?");
			imaArgumenata = true;
			listaArgumenata.add(isbn);
		}
		if(brojStranica != 0) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("brojStranica = ?");
			imaArgumenata = true;
			listaArgumenata.add(brojStranica);
		}
		
		if(cena != 0) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("cena <= ?");
			imaArgumenata = true;
			listaArgumenata.add(cena);
		}
		if(autor!=null) {
			autor = "%" + autor + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("autor LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(autor);
		}
		
		if(jezik!=null) {
			jezik = "%" + jezik + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("jezik LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(jezik);
		}
		if(izdavackaKuca!=null) {
			izdavackaKuca = "%" + izdavackaKuca + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("izdavackaKuca LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(izdavackaKuca);
		}
		if(kratakOpis!=null) {
			kratakOpis = "%" + kratakOpis + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("kratakOpis LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(kratakOpis);
		}
		if(pismo!=null) {
			pismo = "%" + pismo + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("pismo LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(pismo);
		}
		if(tipPoveza!=null) {
			tipPoveza = "%" + tipPoveza + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("tipPoveza LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(tipPoveza);
		}
		
		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY id";
		else
			sql=sql + " ORDER BY id";
		System.out.println(sql);
		
		
		return jdbcTemplate.query(sql, new KnjigaRowMapper(), listaArgumenata.toArray());
	}


	@Override
	public List<Knjiga> find(String naziv, Float cena, String autor, String jezik) {
		
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT naziv,autor, cena, jezik from knjige";
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		
		if(naziv!=null) {
			naziv = "%" + naziv + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("naziv LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(naziv);
		}
		
		if(cena != null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("cena >= ?");
			imaArgumenata = true;
			listaArgumenata.add(cena);
		}
		
		if(cena != null) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("f.trajanje <= ?");
			imaArgumenata = true;
			listaArgumenata.add(cena);
		}
		
		if(autor!=null) {
			naziv = "%" + naziv + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("autor LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(naziv);
		}
		
		if(jezik!=null) {
			naziv = "%" + naziv + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("jezik LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(naziv);
		}
		
		if(imaArgumenata)
			sql=sql + whereSql.toString()+ " ORDER BY id";
		else
			sql=sql + " ORDER BY id";
		System.out.println(sql);

		return jdbcTemplate.query(sql, new KnjigaRowMapper(), listaArgumenata.toArray());
		
	}


	@Override
	public List<Knjiga> find(HashMap<String, Object> mapaArgumenata) {
		ArrayList<Object> listaArgumenata = new ArrayList<Object>();
		
		String sql = "SELECT k.id, k.naziv, k.isbn, k.izdavackaKuca, k.autor, k.godinaIzdavanja, k.cena, k.jezik FROM knjige k ";
		
		StringBuffer whereSql = new StringBuffer(" WHERE ");
		boolean imaArgumenata = false;
		
		if(mapaArgumenata.containsKey("naziv")) {
			String naziv = "%" + mapaArgumenata.get("naziv") + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("k.naziv LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("naziv"));
		}
		
		if(mapaArgumenata.containsKey("autor")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("k.autor LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("sala"));
		}
		
		if(mapaArgumenata.containsKey("jezik")) {
			String jezik = "%" + mapaArgumenata.get("jezik") + "%";
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("k.jezik LIKE ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("jezik"));
		}
		
		if(mapaArgumenata.containsKey("cena")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("k.cena >= ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("cena"));
		}
		
		if(mapaArgumenata.containsKey("cena")) {
			if(imaArgumenata)
				whereSql.append(" AND ");
			whereSql.append("k.cena <= ?");
			imaArgumenata = true;
			listaArgumenata.add(mapaArgumenata.get("cena"));
		}
		
		
		if(imaArgumenata)
			sql=sql + whereSql.toString()+" ORDER BY k.id";
		else
			sql=sql + " ORDER BY k.id";
		System.out.println(sql);
		
		return jdbcTemplate.query(sql, new KnjigaRowMapper(), listaArgumenata.toArray());
		}


	@Override
	public List<Knjiga> find2(String naziv, Long isbn, String izdavackaKuca, String autor, LocalDate godinaIzdavanja,
			String kratakOpis, float cena, int brojStranica, String tipPoveza, String pismo, String jezik,
			float prosecnaOcena, Integer zanrId) {
		// TODO Auto-generated method stub
		return null;
	}

}

	
	
	

