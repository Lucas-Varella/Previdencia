package model;

import java.sql.Date;

public class Contribuicao {
	
	private int idContribuicao;
	private Date dataContribuicao;
	private int idConta;
	private double valorContribuicao;
	private String tipoContribuicao;
	
	public Contribuicao(int idConta, double valorContribuicao, String tipoContribuicao) {
		this.idConta = idConta;
		this.valorContribuicao = valorContribuicao;
		this.tipoContribuicao = tipoContribuicao;
	}
	public int getIdContribuicao() {
		return this.idContribuicao;
	}
	public Date getDataContribuicao() {
		return this.dataContribuicao;
	}
	public int getIdConta() {
		return this.idConta;
	}
	public double getValorContribuicao() {
		return this.valorContribuicao;
	}
	public String getTipoContribuicao() {
		return this.tipoContribuicao;
	}
	

}
