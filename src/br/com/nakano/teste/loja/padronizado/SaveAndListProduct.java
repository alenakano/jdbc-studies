package br.com.nakano.teste.loja.padronizado;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.nakano.teste.loja.padronizado.model.Product;

public class SaveAndListProduct {

	public static void main(String[] args) throws SQLException {
		
		Product banco = new Product("Banco", "Banco almofadado");
	
		try(Connection conn = new ConnectionFactory().createConnection()) {
			conn.setAutoCommit(false);
			ProductDAO productDao = new ProductDAO(conn);
			productDao.save(banco);
			
			List<Product> produto = productDao.listProducts();
			produto.forEach(ls -> System.out.println(ls));
		}
		
	}
}
