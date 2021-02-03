package com.vk77492.bigbasketclone.models.user_model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class UsersList implements Serializable {

	@SerializedName("User")
	private List<UserItem> user;

	public List<UserItem> getUser(){
		return user;
	}
}