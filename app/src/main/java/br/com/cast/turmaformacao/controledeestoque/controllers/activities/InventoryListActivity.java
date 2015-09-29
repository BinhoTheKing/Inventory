package br.com.cast.turmaformacao.controledeestoque.controllers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.controllers.adapters.ProductListAdapter;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.model.services.ProductBusinessService;
import br.com.cast.turmaformacao.controledeestoque.util.AsyncUtil;
import br.com.cast.turmaformacao.controledeestoque.util.Synchronizable;

public class InventoryListActivity extends AppCompatActivity implements Synchronizable {

	private ListView listViewInventory;
	private List<Product> products;
	private Product selectedItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_product);
		products = new ArrayList<>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_list_product, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_add_product:
				onMenuAddClick();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void onMenuAddClick() {
		redirectToProductFormActivity(null);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.context_menu_list_product, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.contextMenuEdit:
				onMenuEditClick();
				break;
			case R.id.contextMenuDelete:
				onMenuDeleteClick();
				break;
		}
		return super.onContextItemSelected(item);
	}

	private void onMenuEditClick() {
		redirectToProductFormActivity(selectedItem);
	}

	private void onMenuDeleteClick() {
		ProductBusinessService.delete(selectedItem);
		new AsyncUtil().execute(this);
	}


	private void redirectToProductFormActivity(Product $Product) {
		Intent goToProductFormActivity = new Intent(InventoryListActivity.this, ProductFormActivity.class);
		goToProductFormActivity.putExtra(Product.PRODUCT_PARAM, $Product);
		startActivity(goToProductFormActivity);
	}

	private void initProducts() {
		products = ProductBusinessService.fetchWeb();

	}

	@Override
	protected void onResume() {
		super.onResume();
		new AsyncUtil().execute(this);
	}

	@Override
	public Void synchronize(Integer... params) {

		switch (params[0]) {
			case 1:
				initProducts();
				break;
			case 2:
				bindListViewInventory();
				break;
		}
		return null;
	}

	private void bindListViewInventory() {
		listViewInventory = (ListView) findViewById(R.id.listViewInventory);
		listViewInventory.setAdapter(new ProductListAdapter(this, products));
		registerForContextMenu(listViewInventory);
		listViewInventory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				ListAdapter adapter = listViewInventory.getAdapter();
				selectedItem = (Product) adapter.getItem(position);
				return false;
			}
		});
	}
}
