package br.com.nakano.teste.loja.padronizado;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.nakano.teste.loja.padronizado.model.Category;
import br.com.nakano.teste.loja.padronizado.model.Product;

public class ListProductsByCategory {
	
	public static void main(String[] args) throws SQLException {
		
		try(Connection conn = new ConnectionFactory().createConnection()) {
			
			CategoryDAO categoryDao = new CategoryDAO(conn);
			List<Category> categories = categoryDao.listProductsbyCategory();
			categories.forEach(cat -> {
				System.out.println(cat.getNome());
				for(Product product: cat.getProducts()) {
					System.out.println(cat.getNome() + " - " + product.getNome());
				}
			});

		}
		
	}
}
