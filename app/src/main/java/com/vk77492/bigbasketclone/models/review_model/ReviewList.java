package com.vk77492.bigbasketclone.models.review_model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ReviewList implements Serializable {

	@SerializedName("Review")
	private List<ReviewItem> review;

	public List<ReviewItem> getReview(){
		return review;
	}
}