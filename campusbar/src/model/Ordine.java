package model;

import java.sql.Date;

public class Ordine {
	

	public int getId_ordine() {
		return id_ordine;
	}
	public void setId_ordine(int id_ordine) {
		this.id_ordine = id_ordine;
	}
	public String getNota_ordine() {
		return nota_ordine;
	}
	public void setNota_ordine(String nota_ordine) {
		this.nota_ordine = nota_ordine;
	}
	public Date getData_ordine() {
		return data_ordine;
	}
	public void setData_ordine(Date data_ordine) {
		this.data_ordine = data_ordine;
	}
	public Boolean getConsegnato() {
		return consegnato;
	}
	public void setConsegnato(Boolean consegnato) {
		this.consegnato = consegnato;
	}
	
	public int getId_edificio() {
		return id_edificio;
	}
	public void setId_edificio(int id_edificio) {
		this.id_edificio = id_edificio;
	}
	
	
	private int id_ordine;
	private String nota_ordine;
	private Date data_ordine;
	private Boolean consegnato;
	private int id_edificio;
}
