package br.com.nakano.teste.loja.padronizado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.nakano.teste.loja.padronizado.model.Product;

public class ProductDAO {
	
	private Connection connection;

	public ProductDAO (Connection conn) {
		this.connection = conn;
	}
	
	public void save(Product produto) throws SQLException {
		
		/* Try with resources para fechar automaticamente PreparedStatement (extends AutoCloseable) */
		try (PreparedStatement stm = 
				connection.prepareStatement("INSERT INTO PRODUTO(nome, descricao) VALUES (? , ?)", Statement.RETURN_GENERATED_KEYS);
			) {		
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getDescricao());
			stm.execute();
			
			connection.commit();
			
			try (ResultSet result = stm.getGeneratedKeys()) {
				while(result.next()) {
					produto.setId(result.getInt(1));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Realizando rollback");
			connection.rollback();
		}

	}
	
	public List<Product> listProducts() throws SQLException {
		List<Product> produtos = new ArrayList<Product>();
		
		try(PreparedStatement stm = this.connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO")) {
			
			stm.execute();
			
			try(ResultSet resultado = stm.getResultSet()) {
				
				while(resultado.next()) {
					Product produto = new Product(resultado.getInt(1), resultado.getString(2), resultado.getString(3));
					produtos.add(produto);
				}
			}
		}
		return produtos;
	}
	
	
}