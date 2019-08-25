package model;

public class Utente {
	
	public Utente() {}
	public Utente(String matricola, String nome, String cognome, String email, String password,Edificio edificio, Ruolo ruolo) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.edificio = edificio;
		this.ruolo = ruolo;
	}
	
	public String getMatricola() {
		return matricola;
	}
	
	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}
	

	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public Edificio getEdificio() {
		return edificio;
	}
	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	private String matricola;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private Ruolo ruolo;
	private Edificio edificio;
}
