package model;

import java.sql.Date;

public class Participante {
	
	private int idParticipante;
	private Date dataCriacao;
	private Date dataNascimento;
	private String nomeParticipante;
	private int idConta;
	
	public Participante(Date dataNascimento, String nomeParticipante) {
		this.dataNascimento = dataNascimento;
		this.nomeParticipante = nomeParticipante;
	}
	public int getIdParticipante() {
		return this.idParticipante;
	}
	public Date getDataCriacao() {
		return this.dataCriacao;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataNascimento() {
		return this.dataNascimento;
	}
	public void setNomeParticipante(String nomeParticipante) {
		this.nomeParticipante = nomeParticipante;
	}
	public String getNomeParticipante() {
		return this.nomeParticipante;
	}
	public int getIdConta() {
		return this.idConta;
	}
	

}
