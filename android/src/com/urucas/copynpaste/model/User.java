package com.urucas.copynpaste.model;

import java.util.ArrayList;

public class User {

	private int id;
	private String imei, code;
	private ArrayList<Post> posts;
	
	public User(int id, String imei, String code) {
		this.id = id;
		this.imei = imei;
		this.code = code;
		this.posts = new ArrayList<Post>();
	}
	
	public int getId() {
		return id;
	}
	
	public ArrayList<Post> addPost(Post post) {
		posts.add(post);
		return posts;
	}
	
	public ArrayList<Post> addPosts(ArrayList<Post> _posts) {
		int len = _posts.size();
		for(int i=0; i < len; i++) {
			posts.add(_posts.get(i));
		}
		return posts;
	}
	
	public ArrayList<Post> getPosts() {
		return posts;
	}

	public String getCode() {
		return code;
	}

	public String getImei() {
		return imei;
	}
}
