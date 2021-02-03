package com.vk77492.bigbasketclone.models.user_model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class CartItem implements Serializable {

	@SerializedName("itemId")
	private int itemId;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("price")
	private double price;

	@SerializedName("isAvailable")
	private boolean isAvailable;

	public int getItemId(){
		return itemId;
	}

	public int getQuantity(){
		return quantity;
	}

	public double getPrice(){
		return price;
	}

	public boolean isIsAvailable(){
		return isAvailable;
	}
}