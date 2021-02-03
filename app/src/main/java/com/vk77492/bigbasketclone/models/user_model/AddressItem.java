package com.vk77492.bigbasketclone.models.user_model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class AddressItem implements Serializable {

	@SerializedName("address")
	private String address;

	@SerializedName("state")
	private String state;

	@SerializedName("city")
	private String city;

	@SerializedName("pinCode")
	private String pinCode;

	public String getAddress(){
		return address;
	}

	public String getState(){
		return state;
	}

	public String getCity(){
		return city;
	}

	public String getPinCode(){
		return pinCode;
	}
}