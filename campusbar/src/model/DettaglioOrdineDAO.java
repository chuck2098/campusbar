package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DettaglioOrdineDAO {

	public boolean doSaveOrUpdateCart(DettaglioOrdine d) {
		
		try(Connection con = ConnectionPool.getConnection()) {
			
			//se la quantita non e' disponibilie in nessun bar,esco
			if(!new DisponibilitaDAO().doCheckByQuantityAndProduct(d.getProdotto(),d.getQuantita()))
				return false;
			  
			ResultSet rs;
		    PreparedStatement ps0 = con.prepareStatement("SELECT id_dettaglio_ordine "
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
	
	//prelevo tutti i prodotti di un ordine dato l'id ordine
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
	//30 prodotti piu venduti
	//id_prodotto is not null poiche' quando si elimina un prodotto 
	//le righe relative ai dettagli_ordine con quel prodotto, rimangono(per poter fare i totali) 
	//ma l'id_prodotto viene settato a NULL
	public List<Prodotto> doRetrieveProductMostSold() {	
		try(Connection con = ConnectionPool.getConnection()){

			PreparedStatement ps = con
					.prepareStatement("SELECT sum(quantita) AS quantita, id_prodotto " + 
										"FROM dettaglio_ordini " + 
										"WHERE prodotto_ordinato =1 AND id_prodotto IS NOT NULL " + 
										"GROUP BY id_prodotto " + 
										"ORDER BY quantita DESC " + 
										"LIMIT 30 ");

			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(2);	
				prodotti.add(new ProdottoDAO().doRetrieveById(id));
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//potrebbe restituire prodotti con id null,qauesto eprche' sono stati eliminati.
	public ArrayList<Totale> doRetrieveAmountByEdificioAndByDateStartDateEnd(Edificio ed,String dateStart,String dateEnd) {	
		
		ArrayList<Totale> totali= new ArrayList<>();
		Totale t;
		try(Connection con = ConnectionPool.getConnection()){

			PreparedStatement ps = con
					.prepareStatement("SELECT id_prodotto,quantita,prezzo_acquisto" + 
										"FROM dettaglio_ordini d,ordini o " + 
										"WHERE d.id_ordine=o.id_ordine AND consegnato=? AND id_edificio=? AND data_ordine BETWEEN ? AND ? " + 
										"GROUP BY id_prodotto " + 
										"ORDER BY totale DESC ");

			ps.setBoolean(1, true); //gli ordini consegnati
			ps.setInt(2,ed.getId_edificio());
			ps.setString(3,dateStart);
			ps.setString(4,dateEnd);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				t=new Totale();
				t.setProdotto(new ProdottoDAO().doRetrieveById(rs.getInt(1)));
				t.setQuantita(rs.getInt(2));
				t.setPrezzo_acquisto(rs.getFloat(3));
				totali.add(t);
			}
			return totali;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	

	
}
