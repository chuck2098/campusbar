package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
				e.setOrario_chiusura(rs.getInt(3));

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

	public Edificio doRetrieveById(int id) {

		try(Connection con = ConnectionPool.getConnection()){



			PreparedStatement ps = con.prepareStatement("SELECT * "
														+ "FROM macro_edifici "
														+ "where id_edificio=?  ");



			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			Edificio e = new Edificio();

			while (rs.next()) {

				e.setId_edificio(rs.getInt(1));
				e.setNome(rs.getString(2));
				e.setOrario_chiusura(rs.getInt(3));


			}
			rs.close();

			return e;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean doDeletebyId(int id) {

		try(Connection con = ConnectionPool.getConnection()) {


			PreparedStatement ps0 = con.prepareStatement("DELETE FROM macro_edifici "
														+"WHERE id_edificio=? ");
			ps0.setInt(1,id);

			if(ps0.executeUpdate()==0)
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean doUpdate(Edificio ed) {
		
		try(Connection con = ConnectionPool.getConnection()) {


			PreparedStatement ps0 = con.prepareStatement("UPDATE macro_edifici "
														+"SET nome=?,orario_chiusura=? "
														+"WHERE id_edificio=? ");
			ps0.setString(1,ed.getNome());
			ps0.setInt(2, ed.getOrario_chiusura());
			ps0.setInt(3,ed.getId_edificio());

			if(ps0.executeUpdate()==0)
				return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean doSave(Edificio ed) {
		
		try(Connection con = ConnectionPool.getConnection()){
			
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO macro_edifici (nome,orario_chiusura) "
				   +"VALUES(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1,ed.getNome());
			ps.setInt(2,ed.getOrario_chiusura());
			
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
			
			return true;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public ArrayList<Edificio> doRetrieveByName(String patt) {
		
		try(Connection con = ConnectionPool.getConnection()){
			
			PreparedStatement ps = con
					.prepareStatement("SELECT id_edificio,nome,orario_chiusura " + 
									"FROM macro_edifici " + 
									"WHERE nome LIKE '%"+patt+"%'");
			
			
			
			ArrayList<Edificio> edifici = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Edificio ed = new Edificio();
				ed.setId_edificio(rs.getInt(1));
				ed.setNome(rs.getString(2));
				ed.setOrario_chiusura(rs.getInt(3));
				edifici.add(ed);
				
			}
			return edifici;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}
