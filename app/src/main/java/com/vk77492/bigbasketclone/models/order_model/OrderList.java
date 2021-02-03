package com.vk77492.bigbasketclone.models.order_model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class OrderList implements Serializable {

	@SerializedName("Order")
	private List<OrderItem> order;

	public List<OrderItem> getOrder(){
		return order;
	}
}