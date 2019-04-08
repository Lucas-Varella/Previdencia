package model;

import java.sql.Date;

public class Resgate {
	
	private int idResgate;
	private Date dataResgate;
	private int idConta;
	private double valor;
	private String tipoResgate;
	private int numeroParcelas;
	
	public Resgate(int idConta, double valor, String tipoResgate, int numeroParcelas) {
		this.idConta = idConta;
		this.valor = valor;
		this.tipoResgate = tipoResgate;
		this.numeroParcelas = numeroParcelas;
	}
	public int getIdResgate() {
		return this.idResgate;
	}
	public Date getDataResgate() {
		return this.dataResgate;
	}
	public int getIdConta() {
		return this.idConta;		
	}
	public double getValor() {
		return this.valor;
	}
	public String getTipoResgate() {
		return this.tipoResgate;
	}
	public int getNumeroParcelas() {
		return this.numeroParcelas;
	}
	

}
