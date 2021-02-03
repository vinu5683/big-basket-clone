package com.vk77492.bigbasketclone.models.product_model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ProductList implements Serializable {

	@SerializedName("Product")
	private List<ProductItem> product;

	public List<ProductItem> getProduct(){
		return product;
	}
}