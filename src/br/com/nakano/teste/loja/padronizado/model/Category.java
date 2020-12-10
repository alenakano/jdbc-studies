package br.com.nakano.teste.loja.padronizado.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

	public String getNome() {
		return nome;
	}

	private Integer id;
	private String nome;
	private List<Product> products = new ArrayList<>();
	
	public  Category(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", nome=" + nome + "]";
	}

	public List<Product> getProducts() {
		return products;
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}
	
}
