package controller;

import java.sql.*;
import java.util.ArrayList;

import model.Conta;
import model.Contribuicao;
import model.Participante;

public class JdbcController {
	
	private static final JdbcController instance = new JdbcController();
	Connection con;
	
	public JdbcController() {
		jdbcConnect();
	}
	
	public void jdbcConnect() {
		
		//registra JDBC driver
		try {
			Driver d = (Driver)Class.forName("com.filemaker.jdbc.Driver").newInstance();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:filemaker://localhost:2399/Previdencia","Admin", "admin");
			this.con = con;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public Connection getConnection() {
		return con;
	}
	public static JdbcController getInstance() {
		return instance;
	}

	public long createConta() {
		try {
			Statement st = con.createStatement();
			st.execute("INSERT INCO CONTA VALUES (0, 0, 0)");
			ResultSet rs = st.executeQuery("SELECT TOP 1 idConta FROM CONTA ORDER BY dataCadastro DESC");
			return rs.getLong(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	public void modSaldoPortabilidade(long idConta, double saldoPortabilidade) {
		try {
			Statement st = con.createStatement();
			st.execute("UPDATE CONTA SET saldoPortabilidade ="+saldoPortabilidade+" WHERE idConta ="+idConta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ResultSet executeQuery(String query) {
		Statement st;
		try {
			st = con.createStatement();
			return st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public long insertParticipante(String nomeParticipante, String situacaoParticipante) {
		try {
			Statement st = con.createStatement();
			st.execute("INSERT INTO CONTA (saldoPortabilidade, saldoContribuicoesAdicionais, saldoContribuicoesNormais) VALUES (0, 0, 0)");
			ResultSet rs = st.executeQuery("SELECT idConta FROM CONTA ORDER BY dataCadastro DESC");
			int idConta = 0;
			if(rs.next()) {
				idConta = rs.getInt("idConta");
			}
			
			st.execute("INSERT INTO PARTICIPANTE (nomeParticipante, idConta, situacaoParticipante) VALUES ('"+nomeParticipante+"',"+idConta+", '"+situacaoParticipante+"')");
			ResultSet rsPart = st.executeQuery("SELECT * FROM PARTICIPANTE WHERE IDCONTA = "+idConta);
			if(rsPart.next()) {
				int idParticipante = rsPart.getInt("idParticipante");
				Date dataCriacao = rsPart.getDate("dataCriacao");
				MainController.getInstance().newParticipante(idParticipante, dataCriacao, nomeParticipante, idConta, situacaoParticipante);
			}
			
			return idConta;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<Participante> loadParticipantes() {
		ArrayList<Participante> parts = new ArrayList<>();
		int idParticipante;
		Date dataCriacao;
		String nomeParticipante;
		int idConta;
		String situacaoParticipante;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM PARTICIPANTE");
			while(rs.next()) {
				idParticipante = rs.getInt("idParticipante");
				dataCriacao = rs.getDate("dataCriacao");
				nomeParticipante = rs.getString("nomeParticipante");
				idConta = rs.getInt("idConta");
				situacaoParticipante = rs.getString("situacaoParticipante");
				Participante part = new Participante(idParticipante, dataCriacao, nomeParticipante, idConta, situacaoParticipante);
				parts.add(part);
			}
			return parts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parts;
		
	}

	public void removeParticipante(Participante part) {
		try {
			Statement st = con.createStatement();
			st.execute("DELETE FROM PARTICIPANTE WHERE idParticipante = "+part.getIdParticipante());
			st.execute("DELETE FROM CONTA WHERE idConta = "+part.getIdConta());
			ParticipanteController.getInstance().deleteParticipante(part);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void editParticipante(Participante part, String nomeParticipante, String situacaoParticipante) {
		try {
			Statement st = con.createStatement();
			st.execute("UPDATE PARTICIPANTE SET nomeParticipante ='"+nomeParticipante+"', situacaoParticipante = '"+situacaoParticipante+"' WHERE idParticipante ="+part.getIdParticipante());
			ParticipanteController.getInstance().editParticipante(part,nomeParticipante,situacaoParticipante);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Conta findContaById(int idConta) {
		Conta conta = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM CONTA WHERE idConta ="+idConta);
			if(rs.next()) {
				Date dataCadastro = rs.getDate("dataCadastro");
				double saldoPortabilidade = rs.getDouble("saldoPortabilidade");
				double saldoContribuicoesAdicionais = rs.getDouble("saldoContribuicoesAdicionais");
				double saldoContribuicoesNormais = rs.getDouble("saldoContribuicoesNormais");
				conta = new Conta(idConta, dataCadastro, saldoPortabilidade, saldoContribuicoesAdicionais, saldoContribuicoesNormais);
				return conta;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conta;
	}

	public Participante findParticipanteByContaId(int idConta) {
		Participante part = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Participante WHERE idConta ="+idConta);
			if(rs.next()) {
				int idParticipante = rs.getInt("idParticipante");
				Date dataCriacao = rs.getDate("dataCriacao");
				String nomeParticipante = rs.getString("nomeParticipante");
				String situacaoParticipante = rs.getString("situacaoParticipante");
				part = new Participante(idParticipante, dataCriacao, nomeParticipante, idConta, situacaoParticipante);
				return part;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return part;
	}

	public int contribuir(Conta conta, String tipoContribuicao, Double valor) {
		try {
			Statement st = con.createStatement();
			switch(tipoContribuicao) {
			case "PORTABILIDADE" :
				st.execute("UPDATE CONTA SET saldoPortabilidade ="+(conta.getSaldoPortabilidade()+valor)+" WHERE idConta = "+conta.getIdConta());		
				return 0;
			case "ADICIONAL" :
				st.execute("UPDATE CONTA SET saldoContribuicoesAdicionais ="+(conta.getSaldoContribuicoesAdicionais()+valor)+" WHERE idConta = "+conta.getIdConta());		
				return 0;
			case "NORMAL" :
				st.execute("UPDATE CONTA SET saldoContribuicoesNormais ="+(conta.getSaldoContribuicoesNormais()+valor)+" WHERE idConta = "+conta.getIdConta());		
				return 0;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
//
//	public ArrayList<Contribuicao> loadContribuicoes(int idConta) {
//		ArrayList<Contribuicao> contrs = new ArrayList<>();
//		int idContribuicao;
//		Date dataContribuicao;
//		double valorContribuicao;
//		String tipoContribuicao;
//		try {
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery("SELECT * FROM Contribuicao WHERE idConta ="+idConta);
//			while(rs.next()) {
//				idContribuicao = rs.getInt("idContribuicao");
//				dataContribuicao = rs.getDate("dataContribuicao");
//				tipoContribuicao = rs.getString("tipoContribuicao");
//				valorContribuicao = rs.getDouble("valorContribuicao");
//				Contribuicao contr = new Contribuicao(idContribuicao, dataContribuicao, idConta, valorContribuicao, tipoContribuicao);
//				contrs.add(contr);
//			}
//			return contrs;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return contrs;
//	}

	public boolean resgatar(Conta conta, String tipoResgate, double valor, int numeroParcelas) {
		Participante part = findParticipanteByContaId(conta.getIdConta());
		try {
			Statement st = con.createStatement();
			switch(tipoResgate) {
			case "PORTABILIDADE" :
				if(conta.getSaldoPortabilidade() < valor) {
					return false;
				}
				st.execute("UPDATE CONTA SET saldoPortabilidade ="+(conta.getSaldoPortabilidade()-valor)+" WHERE idConta = "+conta.getIdConta());		
				return true;
			case "ADICIONAL" :
				if(conta.getSaldoContribuicoesAdicionais() < valor) {
					return false;
				}
				st.execute("UPDATE CONTA SET saldoContribuicoesAdicionais ="+(conta.getSaldoContribuicoesAdicionais()-valor)+" WHERE idConta = "+conta.getIdConta());		
				return true;
			case "NORMAL" :
				if(conta.getSaldoContribuicoesNormais() < valor) {
					return false;
				}
				st.execute("UPDATE CONTA SET saldoContribuicoesNormais ="+(conta.getSaldoContribuicoesNormais()-valor)+" WHERE idConta = "+conta.getIdConta());		
				return true;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}
	
	
	
		
}
