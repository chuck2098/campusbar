package model;

import java.sql.*;

public class DettaglioOrdineDAO {

	public boolean doSaveCart(DettaglioOrdine d) {
		
		try {
			Connection con = ConnectionPool.getConnection();
			
			PreparedStatement ps0 = con.prepareStatement("SELECT id_bar "
														+ "FROM disponibilita "
														+ "WHERE id_prod=? AND quantita>=?");
			ps0.setInt(1,d.getProdotto().getId());
			ps0.setInt(2,d.getQuantita());
			ResultSet rs = ps0.executeQuery();
			rs.last();
			if(rs.getRow()<=0) return false; //quantita non disponibile in nessun bar
			
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
		      
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
}
