package com.vk77492.bigbasketclone.models.product_model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ProductItem implements Serializable {

	@SerializedName("id")
	private int id;

	@SerializedName("images")
	private List<String> images;

	@SerializedName("title")
	private String title;

	@SerializedName("category")
	private String category;

	@SerializedName("subCategory")
	private String subCategory;

	@SerializedName("isOrganic")
	private boolean isOrganic;

	@SerializedName("price")
	private double price;

	@SerializedName("attribute")
	private String attribute;

	@SerializedName("discount")
	private String discount;

	@SerializedName("offer")
	private int offer;

	@SerializedName("isVeg")
	private boolean isVeg;

	@SerializedName("suggestedItemsTag")
	private String suggestedItemsTag;

	@SerializedName("aboutProduct")
	private String aboutProduct;

	@SerializedName("storageAndUses")
	private String storageAndUses;

	@SerializedName("isAvailable")
	private boolean isAvailable;

	public int getId(){
		return id;
	}

	public List<String> getImages(){
		return images;
	}

	public String getTitle(){
		return title;
	}

	public String getCategory(){
		return category;
	}

	public String getSubCategory(){
		return subCategory;
	}

	public boolean isIsOrganic(){
		return isOrganic;
	}

	public double getPrice(){
		return price;
	}

	public String getAttribute(){
		return attribute;
	}

	public String getDiscount(){
		return discount;
	}

	public int getOffer(){
		return offer;
	}

	public boolean isIsVeg(){
		return isVeg;
	}

	public String getSuggestedItemsTag(){
		return suggestedItemsTag;
	}

	public String getAboutProduct(){
		return aboutProduct;
	}

	public String getStorageAndUses(){
		return storageAndUses;
	}

	public boolean isIsAvailable(){
		return isAvailable;
	}
}