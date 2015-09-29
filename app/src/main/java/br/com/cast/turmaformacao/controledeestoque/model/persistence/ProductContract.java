package br.com.cast.turmaformacao.controledeestoque.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;

public class ProductContract {
	public static final String TABLE = "product";
	public static final String ID = "id";
	public static final String WEB_ID = "web_id";
	public static final String IMAGE_SRC = "image_src";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String AMOUNT = "amount";
	public static final String MIN_AMOUNT = "min_amount";
	public static final String UNIT_PRICE = "unit_price";
	public static final String DATE = "date";
	public static final String FLAG_SYNCH = "flag_synch";

	public static final String[] COLUMNS = {ID, WEB_ID, IMAGE_SRC, NAME, DESCRIPTION, AMOUNT, MIN_AMOUNT, UNIT_PRICE, DATE, FLAG_SYNCH};


	public static String getCreateTableScript() {
		StringBuilder create = new StringBuilder();
		create.append(" CREATE TABLE " + TABLE);
		create.append(" ( ");
		create.append(ID + " INTEGER PRIMARY KEY, ");
		create.append(WEB_ID + " INTEGER, ");
		create.append(IMAGE_SRC + " TEXT, ");
		create.append(NAME + " TEXT, ");
		create.append(DESCRIPTION + " TEXT, ");
		create.append(AMOUNT + " INTEGER, ");
		create.append(MIN_AMOUNT + " INTEGER, ");
		create.append(UNIT_PRICE + " REAL, ");
		create.append(DATE + " INTEGER, ");
		create.append(FLAG_SYNCH + " INTEGER ");
		create.append(" ); ");
		return create.toString();
	}

	public static ContentValues getContentValues(Product $Product) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(ID, $Product.get_Id());
		contentValues.put(WEB_ID, $Product.getWebId());
		contentValues.put(IMAGE_SRC, $Product.getImageSrc());
		contentValues.put(NAME, $Product.getName());
		contentValues.put(DESCRIPTION, $Product.getDescription());
		contentValues.put(AMOUNT, $Product.getAmount());
		contentValues.put(MIN_AMOUNT, $Product.getMinAmount());
		contentValues.put(UNIT_PRICE, $Product.getUnitPrice());
		contentValues.put(DATE, $Product.getDate());
		contentValues.put(FLAG_SYNCH, $Product.isFlagSynch() ? 1 : 0);
		return contentValues;
	}

	public static Product getProduct(Cursor $Cursor) {
		Product product = new Product();
		if (!$Cursor.isBeforeFirst() || $Cursor.moveToNext()) {
			product.set_Id($Cursor.getLong($Cursor.getColumnIndex(ID)));
			product.setWebId($Cursor.getLong($Cursor.getColumnIndex(WEB_ID)));
			product.setImageSrc($Cursor.getString($Cursor.getColumnIndex(IMAGE_SRC)));
			product.setName($Cursor.getString($Cursor.getColumnIndex(NAME)));
			product.setDescription($Cursor.getString($Cursor.getColumnIndex(DESCRIPTION)));
			product.setAmount($Cursor.getLong($Cursor.getColumnIndex(AMOUNT)));
			product.setMinAmount($Cursor.getLong($Cursor.getColumnIndex(MIN_AMOUNT)));
			product.setUnitPrice($Cursor.getDouble($Cursor.getColumnIndex(UNIT_PRICE)));
			product.setDate($Cursor.getLong($Cursor.getColumnIndex(DATE)));
			product.setFlagSynch($Cursor.getInt($Cursor.getColumnIndex(FLAG_SYNCH)) != 0);
		}
		return product;
	}

	public static List<Product> getProducts(Cursor $Cursor) {
		List<Product> products = new ArrayList<>();
		while ($Cursor.moveToNext()) {
			products.add(getProduct($Cursor));
		}
		return products;
	}
}
