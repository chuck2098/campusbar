package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

public List<Categoria> doRetrieveAll() {
		
		try(Connection con = ConnectionPool.getConnection()){
			
			PreparedStatement ps = con
					.prepareStatement("SELECT id_categoria, nome_categoria "
									+ "FROM categorie ");
			ArrayList<Categoria> categorie = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Categoria cat = new Categoria();
				cat.setId_categoria(rs.getInt(1));
				cat.setNomeCategoria(rs.getString(2));
				
				categorie.add(cat);
				
			}
			return categorie;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

public List<Categoria> doRetrieveById() {
	
	try(Connection con = ConnectionPool.getConnection()){
		
		PreparedStatement ps = con
				.prepareStatement("SELECT id_categoria, nome_categoria "
								+ "FROM categorie ");
		ArrayList<Categoria> categorie = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			Categoria cat = new Categoria();
			cat.setId_categoria(rs.getInt(1));
			cat.setNomeCategoria(rs.getString(2));
			
			categorie.add(cat);
			
		}
		return categorie;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}
}
