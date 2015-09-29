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
		Product persist;
		products = ProductBusinessService.findAll();
		webProducts = ProductHTTPService.getProductsFromWeb();
		if (webProducts != null) {
			for (Product wp : webProducts) {
				if (products != null) {
					for (Product p : products) {
						persist = toPersist(wp,p);
						if(persist!=null){
							save(persist);
						}
					}
				}
			}
		}
		if(products!=null){
			for (Product p :
					products) {
				if (p.isFlagSynch()){
					delete(p);
				}
			}
		}


		return findAll();
	}

	private static Product toPersist(Product $P1, Product $P2){
		if($P1.equals($P2)){
			$P1.setFlagSynch(true);
			$P2.setFlagSynch(true);
			if ($P1.getDate()>=$P2.getDate()) {
				return $P1;
			}else{
				return $P2;
			}
		}
		return null;
	}
}
