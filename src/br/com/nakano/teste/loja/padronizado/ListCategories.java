package br.com.nakano.teste.loja.padronizado;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.nakano.teste.loja.padronizado.model.Category;

public class ListCategories {

	public static void main(String[] args) throws SQLException {
	
		try(Connection conn = new ConnectionFactory().createConnection()) {
			
			CategoryDAO categoryDao = new CategoryDAO(conn);
			List<Category> categories = categoryDao.list();
			categories.forEach(cat -> System.out.println(cat));

		}
		
	}
	
}
