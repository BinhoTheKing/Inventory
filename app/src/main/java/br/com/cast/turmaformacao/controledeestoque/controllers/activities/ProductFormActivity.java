package br.com.cast.turmaformacao.controledeestoque.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.model.services.ProductBusinessService;

public class ProductFormActivity extends AppCompatActivity {
	private Product product;
	private EditText editTextNameProductView;
	private EditText editTextAmountProductView;
	private EditText editTextMinAmountProductView;
	private EditText editTextPriceProductView;
	private EditText editTextDescriptionProductView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_product);
		initProduct();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_form_save, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_save:
				onMenuSaveClick();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void onMenuSaveClick() {
		bindProduct();
		ProductBusinessService.save(product);
		finish();
	}

	private void bindProduct() {
		product.setName(editTextNameProductView.getText().toString());
		product.setAmount(Long.parseLong(editTextAmountProductView.getText().toString()));
		product.setMinAmount(Long.parseLong(editTextMinAmountProductView.getText().toString()));
		product.setUnitPrice(Double.parseDouble(editTextPriceProductView.getText().toString()));
		product.setDescription(editTextDescriptionProductView.getText().toString());
	}

	private void initProduct() {
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			product = extras.getParcelable(Product.PRODUCT_PARAM);
		}
		product = product != null ? product : new Product();
	}

	@Override
	protected void onResume() {
		super.onResume();
		bindLayoutComponents();
	}

	private void bindLayoutComponents() {
		bindEditTextNameProductView();
		bindEditTextAmountProductView();
		bindEditTextMinAmountProductView();
		bindEditTextPriceProductView();
		bindEditTextDescriptionProductView();
	}

	private void bindEditTextDescriptionProductView() {
		editTextDescriptionProductView = (EditText) findViewById(R.id.editTextDescriptionProduct);
		editTextDescriptionProductView.setText(product.getDescription());
	}

	private void bindEditTextPriceProductView() {
		editTextPriceProductView = (EditText) findViewById(R.id.editTextPriceProduct);
		editTextPriceProductView.setText(product.getUnitPrice() != 0.0 ? product.getUnitPrice().toString() : "");
	}

	private void bindEditTextMinAmountProductView() {
		editTextMinAmountProductView = (EditText) findViewById(R.id.editTextMinAmountProduct);
		editTextMinAmountProductView.setText(product.getMinAmount() != 0 ? product.getMinAmount().toString() : "");
	}

	private void bindEditTextAmountProductView() {
		editTextAmountProductView = (EditText) findViewById(R.id.editTextAmountProduct);
		editTextAmountProductView.setText(product.getAmount() != 0 ? product.getAmount().toString() : "");
	}

	private void bindEditTextNameProductView() {
		editTextNameProductView = (EditText) findViewById(R.id.editTextNameProduct);
		editTextNameProductView.setText(product.getName());
	}
}
