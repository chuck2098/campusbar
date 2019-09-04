package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public Categoria doRetrieveById(int id) {
	
	try(Connection con = ConnectionPool.getConnection()){
		 Categoria c =new Categoria();
		PreparedStatement ps = con
				.prepareStatement("SELECT id_categoria, nome_categoria "
								+ "FROM categorie "
								+ "WHERE id_categoria=?");
		ps.setInt(1,id);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			
			 c.setId_categoria(rs.getInt(1));
			 c.setNomeCategoria(rs.getString(2));
		}
		return c;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}
public ArrayList<Categoria> doRetrieveByName(String patt) {
	
	try(Connection con = ConnectionPool.getConnection()){
		
		PreparedStatement ps = con
				.prepareStatement("SELECT id_categoria,nome_categoria " + 
								"FROM categorie " + 
								"WHERE nome_categoria LIKE '%"+patt+"%'");
				
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
public boolean doDeletebyId(int id) {

	try(Connection con = ConnectionPool.getConnection()) {


		PreparedStatement ps0 = con.prepareStatement("DELETE FROM categorie "
													+"WHERE id_categoria=? ");
		ps0.setInt(1,id);

		if(ps0.executeUpdate()==0)
			return false;

	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}

	return true;
}
public boolean doSave(Categoria cat) {
	
	try(Connection con = ConnectionPool.getConnection()){
		
		PreparedStatement ps = con.prepareStatement(
				"INSERT INTO categorie (nome_categoria) "
			   +"VALUES(?)",
				Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1,cat.getNomeCategoria());
		
		if (ps.executeUpdate() != 1) {
			throw new RuntimeException("INSERT error.");
		}
		
		return true;
		
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	
}
public boolean doUpdate(Categoria cat) {
	
	try(Connection con = ConnectionPool.getConnection()) {


		PreparedStatement ps0 = con.prepareStatement("UPDATE categorie "
													+"SET nome_categoria=? "
													+"WHERE id_categoria=? ");

		ps0.setString(1,cat.getNomeCategoria());
		ps0.setInt(2, cat.getId_categoria());
		
		if(ps0.executeUpdate()==0)
			return false;

	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}

	return true;
}

}
