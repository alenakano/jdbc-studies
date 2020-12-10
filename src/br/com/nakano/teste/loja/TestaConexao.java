package br.com.nakano.teste.loja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaConexao {
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		try (Connection conn = connectionFactory.createConnection()){
			/* Forma passível de SQL injection */
//			Statement stm = conn.createStatement();
//			stm.execute("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
			
			try(PreparedStatement stm = conn.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO")) {
			
				stm.execute();
				
				try(ResultSet resultado = stm.getResultSet()) {
					
					while(resultado.next()) {
						System.out.println(resultado.getInt("ID"));
						System.out.println(resultado.getString("NOME"));
						System.out.println(resultado.getString("DESCRICAO"));
					}
				}
			}
		}
	}

}
