package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

public class OrdineDAO {
	
	public synchronized void doSaveByUser(Ordine o,Utente u) {
			
			try(Connection con = ConnectionPool.getConnection()){
				
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO ordini (nota_ordine,data_ordine,id_edificio) "
					   +"VALUES(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1,o.getNota_ordine());
				
				int anno = o.getData_ordine().get(GregorianCalendar.YEAR);
				int mese = o.getData_ordine().get(GregorianCalendar.MONTH) + 1;
				int giorno = o.getData_ordine().get(GregorianCalendar.DAY_OF_MONTH);
				int ore = o.getData_ordine().get(GregorianCalendar.HOUR_OF_DAY);//formato 24 ore
				int minuti = o.getData_ordine().get(GregorianCalendar.MINUTE);
				int secondi = o.getData_ordine().get(GregorianCalendar.SECOND);
				
				ps.setString(2,""+anno+"-"+mese+"-"+giorno+" "+ore+":"+minuti+":"+secondi);
				ps.setInt(3,o.getId_edificio().getId_edificio());
				
				if (ps.executeUpdate() != 1) {
					throw new RuntimeException("INSERT error.");
				}
				
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				//prelevo l ultimo id inserito
				int id_ord=rs.getInt(1);
				
				ps = con.prepareStatement("UPDATE dettaglio_ordini "
										+ "SET prodotto_ordinato=?,id_ordine=? "
										+ "WHERE prodotto_ordinato=? AND id_utente=?");
				ps.setBoolean(1, true);
				ps.setInt(2,id_ord);
				ps.setBoolean(3, false);
				ps.setString(4,u.getMatricola());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
