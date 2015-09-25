package br.com.cast.turmaformacao.controledeestoque.model.services;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.model.persistence.ProductRepository;

public class ProductBusinessService {

	public ProductBusinessService(){
		super();
	}

	public static List<Product> findAll(){
		return ProductRepository.getAll();
	}

	public static void save(Product $Product){
		ProductRepository.save($Product);
	}

	public static void delete(Product $Product){
		ProductRepository.delete($Product.getId());
	}

	public static void update(Product $Product){
		ProductRepository.save($Product);
	}
}
