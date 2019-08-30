package model;

import java.sql.*;
import java.util.ArrayList;

public class DettaglioOrdineDAO {

	public boolean doSaveOrUpdateCart(DettaglioOrdine d) {
		
		try(Connection con = ConnectionPool.getConnection()) {
			
			
			PreparedStatement ps0 = con.prepareStatement("SELECT id_bar "
														+ "FROM disponibilita "
														+ "WHERE id_prod=? AND quantita>=?");
			ps0.setInt(1,d.getProdotto().getId());
			ps0.setInt(2,d.getQuantita());
			ResultSet rs = ps0.executeQuery();
			rs.last();
			if(rs.getRow()<=0) return false; //quantita non disponibile in nessun bar
			
		    ps0 = con.prepareStatement("SELECT id_dettaglio_ordine "
								     + "FROM dettaglio_ordini "
									 + "WHERE id_prodotto=? AND prodotto_ordinato=0 AND id_utente=?");
		    
		    ps0.setInt(1,d.getProdotto().getId());
		    ps0.setString(2,d.getCliente().getMatricola());
		    
		    rs=ps0.executeQuery();
		    rs.last();
		    
		    //se prodotto e' gia' nel carrello dell'utente loggato
		    if(rs.getRow()>0) {
		    	rs.beforeFirst();//muove il cursore alla prima riga
		    	rs.next();
		    	int id_dettaglio=rs.getInt(1);
		    	
		    	PreparedStatement ps = con.prepareStatement(
						"UPDATE dettaglio_ordini "
					   + "SET quantita=quantita+?,nota_prodotto=? "
					   + "WHERE id_dettaglio_ordine=?");
		    	ps.setInt(1,d.getQuantita());
		    	ps.setString(2,d.getNota());
		    	ps.setInt(3,id_dettaglio); //prelevo l'id dettaglio dalla query fatta sopra
		    	
		    	ps.executeUpdate();
					   
		    }else {
		    	
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO dettaglio_ordini (nota_prodotto,quantita,prezzo_acquisto,prodotto_ordinato,id_utente,id_prodotto) "
					   +"VALUES(?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, d.getNota());
				ps.setInt(2,d.getQuantita());
				ps.setFloat(3,d.getPrezzo_acquisto());
				ps.setBoolean(4,d.isProdotto_ordinato());
				ps.setString(5,d.getCliente().getMatricola());
				ps.setInt(6,d.getProdotto().getId());
				
				
				if (ps.executeUpdate() != 1) {
					throw new RuntimeException("INSERT error.");
				}
		    }

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
	//CARRELLO:funzione per prelevare tutti i dettagli ordini non confermati dell'utente loggagto.
	public ArrayList<DettaglioOrdine> doRetrieveNotConfirmedByUser(Utente u) {
		
		try(Connection con = ConnectionPool.getConnection()){
			
			
			PreparedStatement ps = con.prepareStatement("SELECT * " + 
														"FROM dettaglio_ordini " + 
														"WHERE prodotto_ordinato=0 AND id_utente=? ");
			
			ps.setString(1,u.getMatricola());
			
			ResultSet rs = ps.executeQuery();
			ArrayList<DettaglioOrdine> e = new ArrayList<>();
			
			while(rs.next()) {
				DettaglioOrdine d =new DettaglioOrdine();
				d.setId_dettaglio(rs.getInt(1));
				d.setCliente(u);
				d.setNota(rs.getString(2));
				d.setPrezzo_acquisto(rs.getFloat(4));
				d.setQuantita(rs.getInt(3));
				d.setProdotto_ordinato(rs.getBoolean(5));
				d.setProdotto(new ProdottoDAO().doRetrieveById(rs.getInt(7)));
				
				e.add(d);
				
			}
			return e;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public ArrayList<DettaglioOrdine> doRetrieveConfirmedByOrderId(int id_order) {
		
		try(Connection con = ConnectionPool.getConnection()){
			
			
			PreparedStatement ps = con.prepareStatement("SELECT id_dettaglio_ordine,nota_prodotto,quantita,prezzo_acquisto,id_prodotto,id_utente " + 
														"FROM dettaglio_ordini " + 
														"WHERE id_ordine=?");
			
			ps.setInt(1,id_order);
			
			ResultSet rs = ps.executeQuery();
			ArrayList<DettaglioOrdine> e = new ArrayList<>();
			
			while(rs.next()) {
				DettaglioOrdine d =new DettaglioOrdine();
				d.setId_dettaglio(rs.getInt(1));
				d.setNota(rs.getString(2));
				d.setQuantita(rs.getInt(3));
				d.setPrezzo_acquisto(rs.getFloat(4));
				d.setProdotto_ordinato(true);
				d.setProdotto(new ProdottoDAO().doRetrieveById(rs.getInt(5)));
				d.setCliente(new UtenteDAO().doRetrieveByMatricola(rs.getString(6)));
				
				e.add(d);
				
			}
			return e;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public boolean doDeleteById(int id) {
		
		try(Connection con = ConnectionPool.getConnection()) {
			
			
			PreparedStatement ps0 = con.prepareStatement("DELETE FROM dettaglio_ordini "
														+"WHERE id_dettaglio_ordine=?");
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
