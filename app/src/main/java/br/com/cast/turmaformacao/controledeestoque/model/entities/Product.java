package br.com.cast.turmaformacao.controledeestoque.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
	//ToDo implementar atributo imagem
	private Long id;
	private String name;
	private String description;
	private Long amount;
	private Long minAmount;
	private Double unitPrice;
	public static String PRODUCT_PARAM = "PRODUCT";

	public Product() {
		super();
		name = "";
		description = "";
		amount = (long) 0;
		minAmount = (long) 0;
		unitPrice = 0.0;
	}

	protected Product(Parcel in) {
		readFromParcel(in);
	}

	private void readFromParcel(Parcel in) {
		name = in.readString();
		description = in.readString();
		amount = in.readLong();
		minAmount = in.readLong();
		unitPrice = in.readDouble();
	}

	public static final Creator<Product> CREATOR = new Creator<Product>() {
		@Override
		public Product createFromParcel(Parcel in) {
			return new Product(in);
		}

		@Override
		public Product[] newArray(int size) {
			return new Product[size];
		}
	};

	public Long getId() {
		return id;
	}

	public void setId(Long $Id) {
		id = $Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String $Name) {
		name = $Name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String $Description) {
		description = $Description;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long $Amount) {
		amount = $Amount;
	}

	public Long getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Long $MinAmount) {
		minAmount = $MinAmount;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double $UnitPrice) {
		unitPrice = $UnitPrice;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		if (!name.equals(product.name)) return false;
		if (!description.equals(product.description)) return false;
		if (!amount.equals(product.amount)) return false;
		if (!minAmount.equals(product.minAmount)) return false;
		return unitPrice.equals(product.unitPrice);

	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + description.hashCode();
		result = 31 * result + amount.hashCode();
		result = 31 * result + minAmount.hashCode();
		result = 31 * result + unitPrice.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Product{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", amount=" + amount +
				", minAmount=" + minAmount +
				", unitPrice=" + unitPrice +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name != null ? name : "");
		dest.writeString(description != null ? description : "");
		dest.writeLong(amount != null ? amount : 0);
		dest.writeLong(minAmount != null ? minAmount : 0);
		dest.writeDouble(unitPrice != null ? unitPrice : 0.0);
	}
}
