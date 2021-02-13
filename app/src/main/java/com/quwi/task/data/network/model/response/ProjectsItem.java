package com.quwi.task.data.network.model.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProjectsItem{

	@SerializedName("uid")
	private String uid;

	@SerializedName("is_owner_watcher")
	private boolean isOwnerWatcher;

	@SerializedName("is_active")
	private int isActive;

	@SerializedName("logo_url")
	private String logoUrl;

	@SerializedName("name")
	private String name;

	@SerializedName("dta_user_since")
	private String dtaUserSince;

	@SerializedName("id")
	private int id;

	@SerializedName("position")
	private int position;

	@SerializedName("users")
	private List<UsersItem> users;

	public String getUid(){
		return uid;
	}

	public boolean isIsOwnerWatcher(){
		return isOwnerWatcher;
	}

	public int getIsActive(){
		return isActive;
	}

	public String getLogoUrl(){
		return logoUrl;
	}

	public String getName(){
		return name;
	}

	public String getDtaUserSince(){
		return dtaUserSince;
	}

	public int getId(){
		return id;
	}

	public int getPosition(){
		return position;
	}

	public List<UsersItem> getUsers(){
		return users;
	}
}