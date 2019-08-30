package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtenteDAO {

	public Utente doRetrieveByMatricolaPassword(String matricola, String password) {
		
		try(Connection con = ConnectionPool.getConnection()){
			
			
			PreparedStatement ps = con.prepareStatement(
					"SELECT matricola,nome,cognome,email,password,id_ruolo,id_edificio "
				  + "FROM utenti "
				  + "WHERE matricola=? AND password=? ");
			
			ps.setString(1, matricola);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Utente u = new Utente();
				u.setMatricola(rs.getString(1));
				u.setNome(rs.getString(2));
				u.setCognome(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setRuolo(getRuolo(con,rs.getInt(6)));
				u.setEdificio(getEdificio(con,rs.getInt(7)));
				return u;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void doSave(Utente utente) {
		
		try(Connection con = ConnectionPool.getConnection()){
			
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO utenti (matricola, nome,cognome,email,password,id_edificio,id_ruolo) "
				   +"VALUES(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, utente.getMatricola());
			ps.setString(2, utente.getNome());
			ps.setString(3, utente.getCognome());
			ps.setString(4, utente.getEmail());
			ps.setString(5, utente.getPassword());
			ps.setInt(6, utente.getEdificio().getId_edificio());
			ps.setInt(7, utente.getRuolo().getId_ruolo());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static Ruolo getRuolo(Connection con, int idRuolo) throws SQLException {
		PreparedStatement ps = con.prepareStatement(
				"SELECT c.id_ruolo,c.nome_ruolo " + 
				"FROM ruoli c " + 
				"WHERE c.id_ruolo=?");

		ps.setInt(1, idRuolo);
		
		ResultSet rs = ps.executeQuery();
		Ruolo c=null;
		while (rs.next()) {
		    c = new Ruolo();
			c.setId_ruolo(rs.getInt(1));
			c.setNome_ruolo(rs.getString(2));
		}
		return c;
	}
	private static Edificio getEdificio(Connection con, int idEdificio) throws SQLException {
		PreparedStatement ps = con.prepareStatement(
				"SELECT nome,id_edificio,orario_chiusura " + 
				"FROM macro_edifici  " + 
				"WHERE id_edificio=?");

		ps.setInt(1, idEdificio);
		
		ResultSet rs = ps.executeQuery();
		Edificio c=null;
		while (rs.next()) {
		    c = new Edificio();
			c.setId_edificio(rs.getInt(2));
			c.setOrario_chiusura(rs.getInt(3));
			c.setNome(rs.getString(1));
		}
		return c;
	}
	
public Utente doRetrieveByMail(String email) {

		
		try(Connection con = ConnectionPool.getConnection()){
			
			
			PreparedStatement ps = con.prepareStatement(
					"SELECT matricola,nome,cognome,email,password,id_ruolo,id_edificio "
				  + "FROM utenti "
				  + "WHERE email=?");
			
			ps.setString(1, email);
			
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Utente u = new Utente();
				u.setMatricola(rs.getString(1));
				u.setNome(rs.getString(2));
				u.setCognome(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setPassword(rs.getString(5));
				u.setRuolo(getRuolo(con,rs.getInt(6)));
				u.setEdificio(getEdificio(con,rs.getInt(7)));
				return u;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
public Utente doRetrieveByMatricola(String matricola) {
	
	try(Connection con = ConnectionPool.getConnection()){
		
		
		PreparedStatement ps = con.prepareStatement(
				"SELECT matricola,nome,cognome,email,password,id_ruolo,id_edificio "
			  + "FROM utenti "
			  + "WHERE matricola=?");
		
		ps.setString(1, matricola);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			Utente u = new Utente();
			u.setMatricola(rs.getString(1));
			u.setNome(rs.getString(2));
			u.setCognome(rs.getString(3));
			u.setEmail(rs.getString(4));
			u.setPassword(rs.getString(5));
			u.setRuolo(getRuolo(con,rs.getInt(6)));
			u.setEdificio(getEdificio(con,rs.getInt(7)));
			return u;
		}
		return null;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	
}
}

