package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisponibilitaDAO  {
	
	public ArrayList<Disponibilita> doRetrieveByEdificioAndCategoria(Edificio e,Categoria c) {
		Disponibilita d=null;
		ArrayList<Disponibilita> disp=new ArrayList<>();
		
		try(Connection con = ConnectionPool.getConnection()){
			
			PreparedStatement ps = con
					.prepareStatement("SELECT id_prod,quantita "
									+ "FROM disponibilita,prodotti "
									+ "WHERE id_prod=id_prodotto AND id_bar=? AND id_categoria=?");
			
			ps.setInt(1,e.getId_edificio());
			ps.setInt(2,c.getId_categoria());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				d = new Disponibilita();
				d.setProdotto(new ProdottoDAO().doRetrieveById(rs.getInt(1)));
				d.setQuantita(rs.getInt(2));
				disp.add(d);
			}
			
			return disp;
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
	}
	
	public boolean doCheckByQuantityAndProduct(Prodotto p,int quant) {
		
		try(Connection con = ConnectionPool.getConnection()){
			
			PreparedStatement ps0 = con.prepareStatement("SELECT id_bar "
					+ "FROM disponibilita "
					+ "WHERE id_prod=? AND quantita>=?");
			ps0.setInt(1,p.getId());
			ps0.setInt(2,quant);
			ResultSet rs = ps0.executeQuery();
			rs.last();
			if(rs.getRow()<=0) return false; //quantita non disponibile in nessun bar
			else return true;
			
		} catch (SQLException e1) {
			throw new RuntimeException(e1);
		}
	}
	
	public boolean doUpdateByEdificio(Disponibilita d) {
		
		try(Connection con = ConnectionPool.getConnection()) {
				
				
				PreparedStatement ps0 = con.prepareStatement("UPDATE disponibilita "
															+"SET quantita=? "
															+"WHERE id_bar=? AND id_prod=? ");
				ps0.setInt(1,d.getQuantita());
				ps0.setInt(2,d.getEdificio().getId_edificio());
				ps0.setInt(3,d.getProdotto().getId());
				
				if(ps0.executeUpdate()==0)
					return false;

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
	
	//dato un carrello di un utente verifica se l'edificio ha le quantita richieste
	public boolean doCheckAvailabilityByEdifcioAndCart(Edificio ed, ArrayList<DettaglioOrdine> cart) {
		PreparedStatement ps;
		
		try(Connection con = ConnectionPool.getConnection()){
			
			for(DettaglioOrdine d:cart) {
				//per ogni prodotto
				Prodotto p=d.getProdotto();
				
				ps= con.prepareStatement("SELECT * "
						  + "FROM disponibilita "
						  + "WHERE id_bar=? AND id_prod=? AND quantita>=?");
				
				ps.setInt(1,ed.getId_edificio());
				ps.setInt(2,p.getId());
				ps.setInt(3,d.getQuantita());
				
				ResultSet rs = ps.executeQuery();
				rs.last();
				//se non c'e' disponibilita',allora ritorna false e desce dalla funzione.
				if(rs.getRow()<=0) return false; 
			}
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		//se arriva qui allora tutti i prodotti del carrello sono disponibili per l'edificio passato come parametro.
		return true;
	}

	//setta la disponibilita a 0
	public boolean doUploadAvailabilityGiveProduct(Edificio edificio, Prodotto p) {
		
		try(Connection con = ConnectionPool.getConnection()) {
			
			PreparedStatement ps0 = con.prepareStatement("INSERT INTO disponibilita "
														+"VALUES (?,?,?)");
			ps0.setInt(1,edificio.getId_edificio());
			ps0.setInt(2,p.getId());
			ps0.setInt(3,0);
			
			if(ps0.executeUpdate()==0)
				return false;
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
}
