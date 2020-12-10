package br.com.nakano.teste.loja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteRemocao {
	
	public static void main(String[] args) throws SQLException {
		
		int idnum = 4; 
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		
		try(Connection conn = connectionFactory.createConnection()) { 
			/* Forma passível de SQL injection */
//			Statement stm = conn.createStatement();
//			stm.execute("DELETE FROM PRODUTO WHERE ID>2");
			
			try(PreparedStatement stm = conn.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?")) {
				stm.setInt(1, idnum);
				
				stm.execute();
				
				System.out.println("Qtds linhas modificadas: " + stm.getUpdateCount());
			}
		}
	}
}
