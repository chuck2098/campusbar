package model;

public class DettaglioOrdine {
	
	
	public int getId_dettaglio() {
		return id_dettaglio;
	}
	public void setId_dettaglio(int id_dettaglio) {
		this.id_dettaglio = id_dettaglio;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public float getPrezzo_acquisto() {
		return prezzo_acquisto;
	}
	public void setPrezzo_acquisto(float prezzo_acquisto) {
		this.prezzo_acquisto = prezzo_acquisto;
	}
	public Utente getCliente() {
		return cliente;
	}
	public void setCliente(Utente cliente) {
		this.cliente = cliente;
	}
	public Prodotto getProdotto() {
		return prodotto;
	}
	public boolean isProdotto_ordinato() {
		return prodotto_ordinato;
	}
	public void setProdotto_ordinato(boolean prodotto_ordinato) {
		this.prodotto_ordinato = prodotto_ordinato;
	}
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}
	
	private int id_dettaglio;
	private String nota;
	private int quantita;
	private float prezzo_acquisto;
	private boolean prodotto_ordinato;
	private Utente cliente;//utente che ha fatto l'ordine
	private Prodotto prodotto;

}
