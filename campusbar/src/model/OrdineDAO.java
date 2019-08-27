package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrdineDAO {
	
	public void doSave(Ordine o) {
			
			try(Connection con = ConnectionPool.getConnection()){
				
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO ordini (nota_ordine,data_ordine,id_edificio) "
					   +"VALUES(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1,o.getNota_ordine());
				ps.setDate(2,new java.sql.Date(o.getData_ordine().getTimeInMillis()));
				ps.setInt(3,o.getId_edificio().getId_edificio());
				
				if (ps.executeUpdate() != 1) {
					throw new RuntimeException("INSERT error.");
				}
				
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				//prelevo l ultimo id inserito
				rs.getInt(1);
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
