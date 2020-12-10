package br.com.nakano.teste.loja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteAdicionaProduto {
	
	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		/* Try with resources para fechar automaticamente Connection (extends AutoCloseable) */
		try(Connection conn = connectionFactory.createConnection()) {
			conn.setAutoCommit(false);
			
			
			/* Forma passível de SQL injection */
//			Statement stm = conn.createStatement();
//			stm.execute("INSERT INTO PRODUTO(nome, descricao) VALUES('Fogão','Fogão quatro bocas')", Statement.RETURN_GENERATED_KEYS);
			
			/* Try with resources para fechar automaticamente PreparedStatement (extends AutoCloseable) */
			try (PreparedStatement stm = 
					conn.prepareStatement("INSERT INTO PRODUTO(nome, descricao) VALUES (? , ?)", Statement.RETURN_GENERATED_KEYS);
				) {		
				cadastrarProduto("TV 29''", "TV LED FULL HD", stm);
				cadastrarProduto("Fone de ouvido", "Fone de ouvido wireless", stm);
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Realizando rollback");
				conn.rollback();
			}
		}
	}

	private static void cadastrarProduto(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		
//		if(nome.contains("Fone")) {
//			throw new RuntimeException("Não foi possível realizar transação");
//		}
		
		stm.execute();
		
		/* Try with resources para fechar automaticamente ResultSet (extends AutoCloseable) */
		try (ResultSet resultado = stm.getGeneratedKeys()) {
			while(resultado.next()) {
				System.out.println("Cadastrando com ID ");
				System.out.println(resultado.getInt(1));
			}
		}
	}
	
}
