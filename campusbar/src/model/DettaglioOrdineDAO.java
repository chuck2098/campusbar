package model;

import java.sql.*;

public class DettaglioOrdineDAO {

	public int doSaveCart(DettaglioOrdine d) {
		
		try {
			Connection con = ConnectionPool.getConnection();
			
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO dettaglio_ordini (nota_prodotto,quantita,prezzo_acquisto,id_utente,id_prodotto) "
				   +"VALUES(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, d.getNota());
			ps.setInt(2,d.getQuantita());
			ps.setFloat(3,d.getPrezzo_acquisto());
			ps.setString(4,d.getCliente().getMatricola());
			ps.setInt(5,d.getProdotto().getId());

			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
		      
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
}
