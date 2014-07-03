package com.urucas.copynpaste.model;

public class Post {

	private int id;
	private String data, fecha;
	
	public Post(int id, String data, String fecha) {
		this.id = id;
		this.data = data;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}
	
	public String getData() {
		return data;
	}
	
	public String getFecha() {
		return fecha;
	}
	
}
