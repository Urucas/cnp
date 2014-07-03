package com.urucas.copynpaste.parser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.urucas.copynpaste.model.Post;

public abstract class PostsParser {

	public static ArrayList<Post> parse(JSONArray jsonArray) {
		
		ArrayList<Post> _posts = new ArrayList<Post>();
		for(int i = 0; i< jsonArray.length(); i++) {
			try {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Post _post = PostParser.parse(jsonObject);
				if(_post != null) {
					_posts.add(_post);
				}
				
			}catch(Exception e) { }
		}
		
		return _posts;
	}
}
