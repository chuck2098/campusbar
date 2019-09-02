package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ProdottoDAO {
	
	public List<Prodotto> doRetrieveAll() {
		
		try(Connection con = ConnectionPool.getConnection()){
			
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
				p.setPrezzo(rs.getFloat(4));
				p.setCategoria(getCategoria(con, p.getId()));
				prodotti.add(p);
				
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//30 prodotti piu venduti
	public List<Prodotto> doRetrieveByProductMostSold() {	
		try(Connection con = ConnectionPool.getConnection()){
			
			PreparedStatement ps = con
					.prepareStatement("SELECT sum(quantita) AS quantita, id_prodotto " + 
										"FROM dettaglio_ordini " + 
										"WHERE prodotto_ordinato =1 " + 
										"GROUP BY id_prodotto " + 
										"ORDER BY quantita DESC " + 
										"LIMIT 30 ");
			
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(2);	
				prodotti.add(doRetrieveById(id));
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
		
	public Prodotto doRetrieveById(int id) {
			Prodotto p=null;
			try(Connection con = ConnectionPool.getConnection()){
				
				PreparedStatement ps = con
						.prepareStatement("SELECT id_prodotto,nome,descrizione,prezzo,id_categoria "
										+ "FROM prodotti "
										+ "WHERE id_prodotto=?");
				ps.setInt(1,id);
	
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					p = new Prodotto();
					p.setId(rs.getInt(1));
					p.setNome(rs.getString(2));
					p.setDescrizione(rs.getString(3));
					p.setPrezzo(rs.getFloat(4));
					p.setCategoria(getCategoria(con, p.getId()));
					
				}
				return p;
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
		
	
	public List<Prodotto> doRetrieveByName(String patt) {
			
			try(Connection con = ConnectionPool.getConnection()){
				
				PreparedStatement ps = con
						.prepareStatement("SELECT id_prodotto,nome,descrizione,prezzo,id_categoria " + 
										"FROM prodotti " + 
										"WHERE nome LIKE '%"+patt+"%'");
				
				//ps.setString(1,patt);
				
				ArrayList<Prodotto> prodotti = new ArrayList<>();
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Prodotto p = new Prodotto();
					p.setId(rs.getInt(1));
					p.setNome(rs.getString(2));
					p.setDescrizione(rs.getString(3));
					p.setPrezzo(rs.getFloat(4));
					p.setCategoria(getCategoria(con, p.getId()));
					prodotti.add(p);
					
				}
				return prodotti;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	
	public List<Prodotto> doRetrieveByIdCategoria(int id) {
		Prodotto p=null;
		try(Connection con = ConnectionPool.getConnection()){
			
			PreparedStatement ps = con
					.prepareStatement("SELECT id_prodotto,nome,descrizione,prezzo,id_categoria "
									+ "FROM prodotti "
									+ "WHERE id_categoria=?");
			ps.setInt(1,id);
			
			ArrayList<Prodotto> prodotti = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Prodotto();
				p.setId(rs.getInt(1));
				p.setNome(rs.getString(2));
				p.setDescrizione(rs.getString(3));
				p.setPrezzo(rs.getFloat(4));
				p.setCategoria(getCategoria(con, p.getId()));
				prodotti.add(p);
				
			}
			return prodotti;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public boolean doDeletebyId(int id) {
	
		try(Connection con = ConnectionPool.getConnection()) {
	
	
			PreparedStatement ps0 = con.prepareStatement("DELETE FROM prodotti "
														+"WHERE id_prodotto=? ");
			ps0.setInt(1,id);
	
			if(ps0.executeUpdate()==0)
				return false;
	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	
		return true;
	}
	
	public boolean doUpdate(Prodotto p) {
		
		try(Connection con = ConnectionPool.getConnection()) {


			PreparedStatement ps0 = con.prepareStatement("UPDATE prodotti "
														+"SET nome=?,descrizione=?,prezzo=?,id_categoria=? "
														+"WHERE id_prodotto=? ");
			ps0.setString(1,p.getNome());
			ps0.setString(2, p.getDescrizione());
			ps0.setFloat(3,p.getPrezzo());
			ps0.setInt(4,p.getCategoria().getId_categoria());
			ps0.setInt(5,p.getId());

			if(ps0.executeUpdate()==0)
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean doSave(Prodotto p) {
		
		try(Connection con = ConnectionPool.getConnection()){
			
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO prodotti (nome,descrizione,prezzo,id_categoria) "
				   +"VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1,p.getNome());
			ps.setString(2,p.getDescrizione());
			ps.setFloat(3,p.getPrezzo());
			ps.setInt(4,p.getCategoria().getId_categoria());
			
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			
			return true;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}

