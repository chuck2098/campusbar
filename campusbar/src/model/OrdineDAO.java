package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrdineDAO {
	
	public synchronized void doSaveByUser(Ordine o,Utente u) {
			
			try(Connection con = ConnectionPool.getConnection()){
				
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO ordini (nota_ordine,data_ordine,id_edificio) "
					   +"VALUES(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1,o.getNota_ordine());
				ps.setString(2,o.getData_ordine());
				ps.setInt(3,o.getEdificio().getId_edificio());
				
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
				
				//per ogni prodotto presente nell'arraylist(carrello),scalo quantita
				for(DettaglioOrdine d: o.getDettaglio()){
					
					ps = con.prepareStatement("UPDATE disponibilita "
											+ "SET quantita=quantita-? "
											+ "WHERE id_bar=? AND id_prod=?");
	
					ps.setInt(1,d.getQuantita());
					ps.setInt(2,o.getEdificio().getId_edificio());
					ps.setInt(3,d.getProdotto().getId());
					ps.executeUpdate();
				}

				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	
	public ArrayList<Ordine> doRetrieveOrderNotDeliveredByUser(Utente u) {
		
		try(Connection con = ConnectionPool.getConnection()){
			
			ArrayList<Ordine> ordini=new ArrayList<>();
			Ordine o=null;
			
			PreparedStatement ps = con.prepareStatement(
					"SELECT nota_ordine,data_ordine,id_ordine "
					+ "FROM ordini "
					+ "WHERE consegnato=? AND id_edificio=?",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setBoolean(1,false); //ordini non consegnati
			ps.setInt(2,u.getEdificio().getId_edificio());
			
			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				o=new Ordine();
				
				o.setNota_ordine(rs.getString(1));
				o.setData_ordine(rs.getString(2));
				o.setConsegnato(false);
				o.setEdificio(u.getEdificio());
				o.setId_ordine(rs.getInt(3));
				o.setDettaglio(new DettaglioOrdineDAO().doRetrieveConfirmedByOrderId(rs.getInt(3)));
				
				ordini.add(o);
			}
			
			return ordini;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean doDeleteById(int id) {
		
		try(Connection con = ConnectionPool.getConnection()) {
			
			
			PreparedStatement ps0 = con.prepareStatement("DELETE FROM ordini "
														+"WHERE id_ordine=?");
			ps0.setInt(1,id);
			if(ps0.executeUpdate()==0)
				return false;
			

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
}
