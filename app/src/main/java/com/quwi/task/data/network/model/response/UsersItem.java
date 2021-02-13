package com.quwi.task.data.network.model.response;

import com.google.gson.annotations.SerializedName;

public class UsersItem{

	@SerializedName("avatar_url")
	private Object avatarUrl;

	@SerializedName("dta_activity")
	private String dtaActivity;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("is_online")
	private boolean isOnline;

	@SerializedName("dta_since")
	private Object dtaSince;

	public Object getAvatarUrl(){
		return avatarUrl;
	}

	public String getDtaActivity(){
		return dtaActivity;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public boolean isIsOnline(){
		return isOnline;
	}

	public Object getDtaSince(){
		return dtaSince;
	}
}