package br.com.cast.turmaformacao.controledeestoque.model.services;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;

public class ProductHTTPService {

	public static final String URL = "http://10.11.21.193:4000/api/v1/products";

	private ProductHTTPService(){
		super();
	}

	public static List<Product> getProductsFromWeb(){
		List<Product> products = null;

		try {
			java.net.URL url = new URL(URL);
			final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			int responseCode = conn.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){
				InputStream inputStream = conn.getInputStream();
				ObjectMapper objectMapper = new ObjectMapper();
				CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, Product.class);
				products = objectMapper.readValue(inputStream, collectionType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return products;
	}

	public static void postProductToWeb(Product $Product){
		try {
			java.net.URL url = new URL(URL);
			final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			OutputStream outputStream = conn.getOutputStream();
			new ObjectMapper().writeValue(outputStream,$Product);
			outputStream.flush();
			outputStream.close();
			int responseCode = conn.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK){
				Log.e("Error","Erro no post");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
