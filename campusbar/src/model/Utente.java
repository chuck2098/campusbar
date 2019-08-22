package model;

public class Utente {
	
	public Utente() {}
	public Utente(String matricola, String nome, String cognome, String email, String password,int edificio, int ruolo) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.id_edificio = edificio;
		this.id_ruolo = ruolo;
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
	public int getRuolo() {
		return id_ruolo;
	}
	public int getEdificio() {
		return  id_edificio;
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

	public void setRuolo(int ruolo) {
		this.id_ruolo = ruolo;
	}

	public void setEdificio(int edificio) {
		this.id_edificio = edificio;
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
	private int id_ruolo;
	private int id_edificio;
}
