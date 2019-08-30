package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

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
	public String getData_ordine() {
		return data_ordine;
	}
	public void setData_ordine(String gregorianCalendar) {
		this.data_ordine = gregorianCalendar;
	}
	public boolean getConsegnato() {
		return consegnato;
	}
	public void setConsegnato(boolean consegnato) {
		this.consegnato = consegnato;
	}

	public Edificio getEdificio() {
		return edificio;
	}
	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}
	public ArrayList<DettaglioOrdine> getDettaglio() {
		return dettaglio;
	}
	public void setDettaglio(ArrayList<DettaglioOrdine> dettaglio) {
		this.dettaglio = dettaglio;
	}


	private int id_ordine;
	private String nota_ordine;
	private String data_ordine;
	private boolean consegnato;
	private Edificio edificio;
	private ArrayList<DettaglioOrdine> dettaglio;
}
