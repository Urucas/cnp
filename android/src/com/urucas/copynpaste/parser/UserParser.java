package com.urucas.copynpaste.parser;

import org.json.JSONObject;

import com.urucas.copynpaste.model.User;

public abstract class UserParser {

	public static User parse(JSONObject jsonObject) {
		try {
			
			int id = jsonObject.getInt("id");
			String imei = jsonObject.getString("imei");
			String code = jsonObject.getString("code");
			return new User(id, imei, code);
			
		}catch(Exception e) {
			return null;
		}
	}
}
