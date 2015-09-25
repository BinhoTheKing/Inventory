package br.com.cast.turmaformacao.controledeestoque.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;

public class ProductContract {
	public static final String TABLE = "product";
	public static final String ID = "id";
	// TODO: 25/09/2015   implementar atributo webID
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String AMOUNT = "amount";
	public static final String MIN_AMOUNT = "min_amount";
	public static final String UNIT_PRICE = "unit_price";
	// TODO: 25/09/2015  implementar atributo imagem

	public static final String[] COLUMNS = {ID, NAME, DESCRIPTION, AMOUNT, MIN_AMOUNT, UNIT_PRICE};


	public static String getCreateTableScript() {
		StringBuilder create = new StringBuilder();
		create.append(" CREATE TABLE " + TABLE);
		create.append(" ( ");
		create.append(ID + " INTEGER PRIMARY KEY, ");
		create.append(NAME + " TEXT, ");
		create.append(DESCRIPTION + " TEXT, ");
		create.append(AMOUNT + " INTEGER, ");
		create.append(MIN_AMOUNT + " INTEGER, ");
		create.append(UNIT_PRICE + " REAL ");
		create.append(" ); ");
		return create.toString();
	}

	public static ContentValues getContentValues(Product $Product) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(ID, $Product.getId());
		contentValues.put(NAME, $Product.getName());
		contentValues.put(DESCRIPTION, $Product.getDescription());
		contentValues.put(AMOUNT, $Product.getAmount());
		contentValues.put(MIN_AMOUNT, $Product.getMinAmount());
		contentValues.put(UNIT_PRICE, $Product.getUnitPrice());
		return contentValues;
	}

	public static Product getProduct(Cursor $Cursor){
		Product product = new Product();
		if(!$Cursor.isBeforeFirst() || $Cursor.moveToNext()){
			product.setId($Cursor.getLong($Cursor.getColumnIndex(ID)));
			product.setName($Cursor.getString($Cursor.getColumnIndex(NAME)));
			product.setDescription($Cursor.getString($Cursor.getColumnIndex(DESCRIPTION)));
			product.setAmount($Cursor.getLong($Cursor.getColumnIndex(AMOUNT)));
			product.setMinAmount($Cursor.getLong($Cursor.getColumnIndex(MIN_AMOUNT)));
			product.setUnitPrice($Cursor.getDouble($Cursor.getColumnIndex(UNIT_PRICE)));
		}
		return product;
	}

	public static List<Product> getProducts(Cursor $Cursor){
		List<Product> products = new ArrayList<>();
		while ($Cursor.moveToNext()){
			products.add(getProduct($Cursor));
		}
		return products;
	}
}
