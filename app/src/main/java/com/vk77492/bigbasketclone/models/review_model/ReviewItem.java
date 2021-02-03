package com.vk77492.bigbasketclone.models.review_model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ReviewItem implements Serializable {

	@SerializedName("userName")
	private String userName;

	@SerializedName("userId")
	private int userId;

	@SerializedName("itemId")
	private int itemId;

	@SerializedName("rated")
	private int rated;

	@SerializedName("review")
	private String review;

	public String getUserName(){
		return userName;
	}

	public int getUserId(){
		return userId;
	}

	public int getItemId(){
		return itemId;
	}

	public int getRated(){
		return rated;
	}

	public String getReview(){
		return review;
	}
}