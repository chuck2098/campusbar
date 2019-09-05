package model;

public class Totale {
	
	public Prodotto getProdotto() {
		return prodotto;
	}
	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
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
	public float getTotale() {
		return prezzo_acquisto*quantita;
	}
	private Prodotto prodotto;
	private int quantita;
	private float prezzo_acquisto;
}
