package br.com.cast.turmaformacao.controledeestoque.controllers.adapters;

import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;

public abstract class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

	private List<Product> products;

	public ProductListAdapter(List<Product> $Products) {
		super();
		products = $Products;
	}

//
//	@Override
//	public int getCount() {
//		return products.size();
//	}
//
//	@Override
//	public Product getItem(int position) {
//		return products.get(position);
//	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Product product = products.get(position);
		if (product != null) {
//			File file = new File(Environment.getDataDirectory()+"/"+Environment.DIRECTORY_DOWNLOADS);
//			Bitmap image = BitmapFactory.decodeFile(Environment.getDataDirectory()+"/"+Environment.DIRECTORY_DOWNLOADS+"/image.jpg"/*product.getImageSrc()*/);


//			holder.productImageView.setImageBitmap(image);
			holder.productNameTextView.setText(product.getName());
			holder.productQtdTextView.setText(product.getAmount().toString());
			holder.productPriceTextView.setText(product.getUnitPrice().toString());
		}
	}


	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemCount() {
		return products.size();
	}

	public abstract void onMenuEditClick(Product p);

	public abstract void onMenuDeleteClick(Product p);

//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		Product product = getItem(position);
//		ViewHolder viewHolder = new ViewHolder();
//
//		if (convertView == null) {
//			convertView = context.getLayoutInflater().inflate(R.layout.list_item_product, parent, false);
//			viewHolder.productImageView = (ImageView) convertView.findViewById(R.id.imageViewProduct);
//			viewHolder.productNameTextView = (TextView) convertView.findViewById(R.id.textViewNameProduct);
//			viewHolder.productQtdTextView = (TextView) convertView.findViewById(R.id.textViewQtdProduct);
//			viewHolder.productPriceTextView = (TextView) convertView.findViewById(R.id.textViewPriceProduct);
//
//			convertView.setTag(viewHolder);
//
//
//		} else {
//			viewHolder = (ViewHolder) convertView.getTag();
//		}
//
//		if (product != null) {
////			File file = new File(Environment.getDataDirectory()+"/"+Environment.DIRECTORY_DOWNLOADS);
////			Bitmap image = BitmapFactory.decodeFile(Environment.getDataDirectory()+"/"+Environment.DIRECTORY_DOWNLOADS+"/image.jpg"/*product.getImageSrc()*/);
//
//
////			viewHolder.productImageView.setImageBitmap(image);
//			viewHolder.productNameTextView.setText(product.getName());
//			viewHolder.productQtdTextView.setText(product.getAmount().toString());
//			viewHolder.productPriceTextView.setText(product.getUnitPrice().toString());
//		}
//
//		return convertView;
//	}

	class ViewHolder extends RecyclerView.ViewHolder {
		ActionMenuView actionMenuCardView;
		ImageView productImageView;
		TextView productNameTextView;
		TextView productQtdTextView;
		TextView productPriceTextView;



		public ViewHolder(View v) {
			super(v);
			productImageView = (ImageView) v.findViewById(R.id.imageViewProduct);
			productNameTextView = (TextView) v.findViewById(R.id.textViewNameProduct);
			productQtdTextView = (TextView) v.findViewById(R.id.textViewQtdProduct);
			productPriceTextView = (TextView) v.findViewById(R.id.textViewPriceProduct);
			actionMenuCardView = (ActionMenuView) v.findViewById(R.id.actionMenuCard);
			MenuInflater menuInflater = new MenuInflater(v.getContext());
			menuInflater.inflate(R.menu.context_menu_list_product, actionMenuCardView.getMenu());
			actionMenuCardView.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					switch (item.getItemId()) {
						case R.id.contextMenuEdit:
							onMenuEditClick(products.get(getAdapterPosition()));
							break;
						case R.id.contextMenuDelete:
							onMenuDeleteClick(products.get(getAdapterPosition()));
							break;
					}
					return false;
				}
			});
		}
	}
}
