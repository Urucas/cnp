package com.urucas.copynpaste.parser;

import org.json.JSONObject;

import com.urucas.copynpaste.model.Post;

public abstract class PostParser {

	public static Post parse(JSONObject jsonObject) {
		try {
			
			int id = jsonObject.getInt("id");
			String data = jsonObject.getString("data");
			String fecha = jsonObject.getString("fecha");
			return new Post(id, data, fecha);
			
		}catch(Exception e) {
			return null;
		}
	}
}
