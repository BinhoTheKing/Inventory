package br.com.cast.turmaformacao.controledeestoque.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;

public class InventoryListAdapter extends BaseAdapter {

	private List<Product> products;
	private Activity context;

	public InventoryListAdapter(Activity $Context, List<Product> $Products) {
		super();
		context = $Context;
		products = $Products;
	}

	@Override
	public int getCount() {
		return products.size();
	}

	@Override
	public Product getItem(int position) {
		return products.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Product product = getItem(position);
		ViewHolder viewHolder = new ViewHolder();

		if (convertView == null) {
			convertView = context.getLayoutInflater().inflate(R.layout.list_item_product, parent, false);
			viewHolder.productImageView = (ImageView) convertView.findViewById(R.id.imageViewProduct);
			viewHolder.productNameTextView = (TextView) convertView.findViewById(R.id.textViewNameProduct);
			viewHolder.productQtdTextView = (TextView) convertView.findViewById(R.id.textViewQtdProduct);
			viewHolder.productPriceTextView = (TextView) convertView.findViewById(R.id.textViewPriceProduct);

			convertView.setTag(viewHolder);


		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (product != null) {
			viewHolder.productNameTextView.setText(product.getName());
			viewHolder.productQtdTextView.setText(product.getAmount().toString());
			viewHolder.productPriceTextView.setText(product.getUnitPrice().toString());
		}

		return convertView;
	}

	static class ViewHolder {
		ImageView productImageView;
		TextView productNameTextView;
		TextView productQtdTextView;
		TextView productPriceTextView;
	}
}
