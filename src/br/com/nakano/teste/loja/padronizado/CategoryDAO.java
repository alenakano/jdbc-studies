package br.com.nakano.teste.loja.padronizado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.nakano.teste.loja.padronizado.model.Category;
import br.com.nakano.teste.loja.padronizado.model.Product;

public class CategoryDAO {
	
	private Connection connection;
	
	public CategoryDAO (Connection conn) {
		this.connection = conn;
	}
	
	public List<Category> list() throws SQLException {
		List<Category> categories = new ArrayList<Category>();
		
		try(PreparedStatement pstm = this.connection.prepareStatement("SELECT ID, NOME FROM CATEGORIA")) {
			
			pstm.execute();
			
			try (ResultSet resultSet = pstm.getResultSet()) {
				while(resultSet.next()) {
					Category category = new Category(resultSet.getInt(1), resultSet.getString(2));
					categories.add(category);
				}
			}
		};
		return categories;
	}
	
	public List<Category> listProductsbyCategory() throws SQLException {
		List<Category> categories = new ArrayList<Category>();
		Category lastCategory = null;
		
		try(PreparedStatement pstm = this.connection.prepareStatement(
				"SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C INNER JOIN PRODUTO P ON C.ID = P.CATEGORIA_ID")) {
			
			pstm.execute();
			
			try (ResultSet resultSet = pstm.getResultSet()) {
				while(resultSet.next()) {
					if(lastCategory == null || lastCategory.getId() != resultSet.getInt(1)) {
						Category category = new Category(resultSet.getInt(1), resultSet.getString(2));
						lastCategory = category;
						categories.add(category);
					}
					Product product = new Product(resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5));
					lastCategory.addProduct(product);
				}
			}
		};
		return categories;
	}

}
