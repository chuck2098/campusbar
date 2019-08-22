package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.*;


public class ProdottoDAO {
	
	public List<Prodotto> doRetrieveAll() {
		
		try{
			Connection con = ConnectionPool.getConnection();
			PreparedStatement ps = con
					.prepareStatement("SELECT id_prodotto,nome,descrizione,prezzo,id_categoria "
									+ "FROM prodotti ");
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Prodotto p = new Prodotto();
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setDescrizione(rs.getString(3));
				p.setPrezzo(rs.getLong(4));
				p.setCategoria(getCategoria(con, p.getId()));
				prodotti.add(p);
				
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static Categoria getCategoria(Connection con, int idProdotto) throws SQLException {
		PreparedStatement ps = con.prepareStatement(
				"SELECT c.id_categoria,nome_categoria " + 
				"FROM categorie c,prodotti p " + 
				"WHERE p.id_prodotto=? AND p.id_categoria=c.id_categoria ");

		ps.setInt(1, idProdotto);
		
		ResultSet rs = ps.executeQuery();
		Categoria c=null;
		while (rs.next()) {
		    c = new Categoria();
			c.setId_categoria(rs.getInt(1));
			c.setNomeCategoria(rs.getString(2));
		}
		return c;
	}
}
