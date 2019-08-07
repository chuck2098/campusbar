package model;

public class Utente {
	public int getMatricola() {
		return matricola;
	}
	
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getRuolo() {
		return ruolo;
	}
	public String getEdificio() {
		return  edificio;
	}
	public  String getPass() {
		return password;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}
	

	private int matricola;
	private String nome;
	private String cognome;
	private String password;
	private String ruolo;
	private String edificio;
}
