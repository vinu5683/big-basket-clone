package com.vk77492.bigbasketclone.models.user_model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class UserItem implements Serializable {

	@SerializedName("userId")
	private int userId;

	@SerializedName("userName")
	private String userName;

	@SerializedName("userImage")
	private String userImage;

	@SerializedName("userEmail")
	private String userEmail;

	@SerializedName("userPassword")
	private String userPassword;

	@SerializedName("userPhoneNumber")
	private String userPhoneNumber;

	@SerializedName("address")
	private List<AddressItem> address;

	@SerializedName("cart")
	private List<CartItem> cart;

	public int getUserId(){
		return userId;
	}

	public String getUserName(){
		return userName;
	}

	public String getUserImage(){
		return userImage;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public String getUserPassword(){
		return userPassword;
	}

	public String getUserPhoneNumber(){
		return userPhoneNumber;
	}

	public List<AddressItem> getAddress(){
		return address;
	}

	public List<CartItem> getCart(){
		return cart;
	}
}