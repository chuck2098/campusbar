package model;

public class Edificio {

	public int getId_edificio() {
		return id_edificio;
	}
	public void setId_edificio(int id_edificio) {
		this.id_edificio = id_edificio;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getOrario_chiusura() {
		return orario_chiusura;
	}
	public void setOrario_chiusura(int orario_chiusura) {
		this.orario_chiusura = orario_chiusura;
	}

	private int id_edificio;
	private String nome;
	private int orario_chiusura;
}
