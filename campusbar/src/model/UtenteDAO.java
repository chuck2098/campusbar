package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtenteDAO {

	public Utente doRetrieveByMatricolaPassword(String matricola, String password) {
		
		try{
			Connection con = ConnectionPool.getConnection();
			
			PreparedStatement ps = con.prepareStatement(
					"SELECT matricola,nome,cognome,email,password,id_ruolo,id_edificio "
				  + "FROM utenti,ruoli "
				  + "WHERE matricola=? AND password=?");
			
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
				u.setRuolo(rs.getInt(6));
				u.setEdificio(rs.getInt(7));
				return u;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void doSave(Utente utente) {
		
		try{
			Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO utenti (matricola, nome,cognome,email,password,id_edificio,id_ruolo) "
				   +"VALUES(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, utente.getMatricola());
			ps.setString(2, utente.getNome());
			ps.setString(3, utente.getCognome());
			ps.setString(4, utente.getEmail());
			ps.setString(5, utente.getPassword());
			ps.setInt(6, utente.getEdificio());
			ps.setInt(7, utente.getRuolo());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
