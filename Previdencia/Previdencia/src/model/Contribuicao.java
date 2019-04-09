package model;

import java.sql.Date;

public class Contribuicao {
	
	private int idContribuicao;
	private Date dataContribuicao;
	private int idConta;
	private double valorContribuicao;
	private String tipoContribuicao;
	
	public Contribuicao(int idContribuicao, Date dataContribuicao, int idConta, double valorContribuicao, String tipoContribuicao) {
		this.idContribuicao = idContribuicao;
		this.dataContribuicao = dataContribuicao;
		this.idConta = idConta;
		this.valorContribuicao = valorContribuicao;
		this.tipoContribuicao = tipoContribuicao;
	}
	public long getIdContribuicao() {
		return this.idContribuicao;
	}
	public Date getDataContribuicao() {
		return this.dataContribuicao;
	}
	public long getIdConta() {
		return this.idConta;
	}
	public double getValorContribuicao() {
		return this.valorContribuicao;
	}
	public String getTipoContribuicao() {
		return this.tipoContribuicao;
	}
	

}
