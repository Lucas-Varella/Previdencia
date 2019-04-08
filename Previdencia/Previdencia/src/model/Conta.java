package model;

import java.sql.Date;
import java.sql.SQLException;

import controller.JdbcController;

public class Conta {
	
	private long idConta;
	private Date dataCadastro;
	private double saldoPortabilidade = 0;
	private double saldoContribuicoesAdicionais = 0;
	private double saldoContribuicoesNormais = 0;
	
	public Conta() {
		long idConta = JdbcController.getInstance().createConta();
		try {
			this.dataCadastro = JdbcController.getInstance().executeQuery("SELECT dataCadastro FROM CONTA WHERE idConta = " +idConta).getDate(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void modSaldoPortabilidade(double valor) {
		this.saldoPortabilidade += valor;
		JdbcController.getInstance().modSaldoPortabilidade(this.idConta, this.saldoPortabilidade);
	}
	public double getSaldoPortabilidade() {
		return this.saldoPortabilidade;
	}
	public void modSaldoContribuicoesAdicionais(double valor) {
		this.saldoContribuicoesAdicionais += valor;
	}
	public double getSaldoContribuicoesAdicionais() {
		return this.saldoContribuicoesAdicionais;
	}
	public void modSaldoContribuicoesNormais(double valor) {
		this.saldoContribuicoesNormais += valor;
	}
	public double getSaldoContribuicoesNormais() {
		return this.saldoContribuicoesNormais;
	}
	public Date getDataCadastro() {
		return this.dataCadastro;
	}
	public long getIdConta() {
		return this.idConta;
	}

}
