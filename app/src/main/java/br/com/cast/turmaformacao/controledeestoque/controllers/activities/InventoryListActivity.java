package br.com.cast.turmaformacao.controledeestoque.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.controllers.adapters.ProductListAdapter;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.model.services.ProductBusinessService;
import br.com.cast.turmaformacao.controledeestoque.util.AsyncUtil;
import br.com.cast.turmaformacao.controledeestoque.util.Synchronizable;

public class InventoryListActivity extends AppCompatActivity implements Synchronizable {

	private RecyclerView recyclerViewInventory;
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
	public Void synchronize(Integer param) {

		switch (param) {
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
		recyclerViewInventory = (RecyclerView) findViewById(R.id.recyclerViewInventory);
		recyclerViewInventory.setLayoutManager(new LinearLayoutManager(this));
		recyclerViewInventory.setAdapter(new ProductListAdapter(products) {
			@Override
			public void onMenuEditClick(Product p) {
				redirectToProductFormActivity(p);
			}

			@Override
			public void onMenuDeleteClick(Product p) {
				new AlertDialog.Builder(InventoryListActivity.this)
						.setMessage(R.string.msg_ask_delete)
						.setNeutralButton(getString(R.string.lbl_no), null)
						.setPositiveButton(getString(R.string.lbl_yes), new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								ProductBusinessService.delete(selectedItem);
								new AsyncUtil().execute(InventoryListActivity.this);
							}
						})
						.create()
						.show();
			}
		});
//		recyclerViewInventory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//			@Override
//			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//				ListAdapter adapter = recyclerViewInventory.getAdapter();
//				selectedItem = (Product) adapter.getItem(position);
//				return false;
//			}
//		});
	}
}
