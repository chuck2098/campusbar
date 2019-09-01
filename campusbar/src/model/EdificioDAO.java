package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EdificioDAO {
	
	public ArrayList<Edificio> doRetrieveAll() {
		
		try(Connection con = ConnectionPool.getConnection()){
			
			PreparedStatement ps_dispo;
			ResultSet rs_dispo;
			ArrayList<Disponibilita> disp = new ArrayList<>();
			Disponibilita d=new Disponibilita();
			
			PreparedStatement ps = con.prepareStatement("SELECT * "
													  + "FROM macro_edifici");
			
			ArrayList<Edificio> edifici = new ArrayList<>();
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Edificio e = new Edificio();
				e.setId_edificio(rs.getInt(1));
				e.setNome(rs.getString(2));
				
				//query per prelevare disponibilita prodotti del bar
				/*ps_dispo = con.prepareStatement("SELECT * "
										 + "FROM disponibilita "
										 + "WHERE id_edificio=?");
				
				ps_dispo.setInt(1,rs.getInt(1));
				rs_dispo = ps_dispo.executeQuery();
				
				disp.clear();
				
				//salvo nell arraylist ogni prodotto con ogni quantita di quell edifcio
				while (rs_dispo.next()) {
					
					Prodotto p=new Prodotto();
					p.setId(rs_dispo.getInt(2));
					d.setProdotto(p);
					d.setQuantita(rs_dispo.getInt(3));
					disp.add(d);
				}
				rs_dispo.close();
				
				
				e.setGiacenze(disp);*/
				
				edifici.add(e);
				
			}
			rs.close();
			
			return edifici;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
