package com.vk77492.bigbasketclone.models.order_model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class OrderItem implements Serializable {

	@SerializedName("userId")
	private int userId;

	@SerializedName("totalPrice")
	private double totalPrice;

	@SerializedName("orderDate")
	private String orderDate;

	@SerializedName("items")
	private List<ItemsItem> items;

	@SerializedName("address")
	private String address;

	@SerializedName("isDelivered")
	private boolean isDelivered;

	@SerializedName("deliveryDate")
	private String deliveryDate;

	public int getUserId(){
		return userId;
	}

	public double getTotalPrice(){
		return totalPrice;
	}

	public String getOrderDate(){
		return orderDate;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	public String getAddress(){
		return address;
	}

	public boolean isIsDelivered(){
		return isDelivered;
	}

	public String getDeliveryDate(){
		return deliveryDate;
	}
}