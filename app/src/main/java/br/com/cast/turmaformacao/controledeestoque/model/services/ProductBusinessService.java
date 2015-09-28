package br.com.cast.turmaformacao.controledeestoque.model.services;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.model.persistence.ProductRepository;

public class ProductBusinessService {

	public ProductBusinessService() {
		super();
	}

	public static List<Product> findAll() {
		return ProductRepository.getAll();
	}

	public static void save(Product $Product) {
		ProductRepository.save($Product);
	}

	public static void delete(Product $Product) {
		ProductRepository.delete($Product.get_Id());
	}

	public static void update(Product $Product) {
		ProductRepository.save($Product);
	}

	public static List<Product> fetchWeb() {
		List<Product> webProducts;
		List<Product> products;
		products = ProductBusinessService.findAll();
		webProducts = ProductHTTPService.getProductsFromWeb();
		if (webProducts != null) {
			for (Product wp : webProducts) {
				for (Product p : products) {
					if (wp.equals(p)) {
						if (wp.getDate() > p.getDate()) {
							p.setFromWeb(wp);
							update(p);
						} else {
							if (p.getDate() > wp.getDate()) {
								ProductHTTPService.postProductToWeb(p);
							}
						}
					} else {
						save(wp);
					}
				}
			}
		}

		return findAll();
	}
}
