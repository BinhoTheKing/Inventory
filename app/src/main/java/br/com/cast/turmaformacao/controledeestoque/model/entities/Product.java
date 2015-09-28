package br.com.cast.turmaformacao.controledeestoque.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product implements Parcelable {
	@JsonIgnore
	private Long _Id;

	@JsonProperty("id")
	private Long webId;

	@JsonProperty("image")
	private String imageSrc;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("stock")
	private Long amount;

	@JsonProperty("minimunStock")
	private Long minAmount;

	@JsonProperty("unitaryValue")
	private Double unitPrice;

	@JsonProperty("date")
	private Long date;

	public static String PRODUCT_PARAM = "PRODUCT";

	public Product() {
		super();
		name = "";
		description = "";
		amount = (long) 0;
		minAmount = (long) 0;
		unitPrice = 0.0;
	}

	public void setFromWeb(Product $FromWeb){
		imageSrc = $FromWeb.imageSrc;
		name = $FromWeb.name;
		description = $FromWeb.description;
		amount = $FromWeb.amount;
		minAmount = $FromWeb.minAmount;
		unitPrice = $FromWeb.unitPrice;
		date = $FromWeb.date;
	}

	protected Product(Parcel in) {
		readFromParcel(in);
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

	public Long get_Id() {
		return _Id;
	}

	public void set_Id(Long $_Id) {
		_Id = $_Id;
	}

	public Long getWebId() {
		return webId;
	}

	public void setWebId(Long $WebId) {
		webId = $WebId;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String $ImageSrc) {
		imageSrc = $ImageSrc;
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

	public Long getDate() {
		return date;
	}

	public void setDate(Long $Date) {
		date = $Date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		return webId.equals(product.webId);

	}

	@Override
	public int hashCode() {
		int result = webId.hashCode();
		result = 31 * result + date.hashCode();
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

	private void readFromParcel(Parcel in) {
		_Id = in.readLong();
		webId = in.readLong();
		imageSrc = in.readString();
		name = in.readString();
		description = in.readString();
		amount = in.readLong();
		minAmount = in.readLong();
		unitPrice = in.readDouble();
		date = in.readLong();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(_Id != null ? _Id : -1);
		dest.writeLong(webId != null ? webId : -1);
		dest.writeString(imageSrc);
		dest.writeString(name != null ? name : "");
		dest.writeString(description != null ? description : "");
		dest.writeLong(amount != null ? amount : 0);
		dest.writeLong(minAmount != null ? minAmount : 0);
		dest.writeDouble(unitPrice != null ? unitPrice : 0.0);
		dest.writeLong(date != null ? date : 0);
	}
}
