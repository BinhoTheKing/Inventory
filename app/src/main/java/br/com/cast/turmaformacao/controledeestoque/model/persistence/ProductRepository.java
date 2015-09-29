package br.com.cast.turmaformacao.controledeestoque.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;

public final class ProductRepository {
	private ProductRepository() {
		super();
	}

	public static void save(Product $Product) {
		DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
		SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
		ContentValues values = ProductContract.getContentValues($Product);
		if ($Product.get_Id() == null) {
			$Product.set_Id(db.insert(ProductContract.TABLE, null, values));
		} else {
			String where = ProductContract.ID + " = ? ";
			String[] params = {$Product.get_Id().toString()};
			db.update(ProductContract.TABLE, values, where, params);
		}
		db.close();
		dataBaseHelper.close();
	}

	public static void delete(Long $Id) {
		DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
		SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

		String where = ProductContract.ID + " = ? ";
		String[] params = {$Id.toString()};
		db.delete(ProductContract.TABLE, where, params);

		db.close();
		dataBaseHelper.close();
	}

	public static Product getByWebId(Long $WebId){
		Product product;
		DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
		SQLiteDatabase db = dataBaseHelper.getWritableDatabase();

		String where = ProductContract.WEB_ID + " = ? ";
		String[] params = {$WebId.toString()};
		Cursor query = db.query(ProductContract.TABLE, ProductContract.COLUMNS, where, params, null, null, null);

		product = ProductContract.getProduct(query);

		db.close();
		dataBaseHelper.close();
		return product;
	}

	public static List<Product> getAll() {
		List<Product> products;

		DataBaseHelper dataBaseHelper = DataBaseHelper.getInstance();
		SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

		Cursor cursor = db.query(ProductContract.TABLE, ProductContract.COLUMNS, null, null, null, null, ProductContract.ID);

		products = ProductContract.getProducts(cursor);

		db.close();
		dataBaseHelper.close();

		return products;
	}
}
