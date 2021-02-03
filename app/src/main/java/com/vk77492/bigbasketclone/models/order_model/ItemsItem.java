package com.vk77492.bigbasketclone.models.order_model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ItemsItem implements Serializable {

	@SerializedName("itemId")
	private int itemId;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("price")
	private double price;

	public int getItemId(){
		return itemId;
	}

	public int getQuantity(){
		return quantity;
	}

	public double getPrice(){
		return price;
	}
}