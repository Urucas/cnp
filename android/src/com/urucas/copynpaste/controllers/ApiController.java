package com.urucas.copynpaste.controllers;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.StrictMode;
import android.util.Log;

import com.urucas.copynpaste.CNPApplication;
import com.urucas.copynpaste.callbacks.PostsCallback;
import com.urucas.copynpaste.callbacks.UserCallback;
import com.urucas.copynpaste.model.User;
import com.urucas.copynpaste.parser.PostsParser;
import com.urucas.copynpaste.parser.UserParser;

import com.urucas.services.JSONRequestTask;
import com.urucas.services.JSONRequestTaskHandler;
import com.urucas.utils.Utils;

public class ApiController {

	private static String BASE_URL = "http://cnp.urucas.com/api";
	
	public void getCode(final UserCallback callback){
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
			// only for gingerbread and newer versions
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		if(!this.isConnected()) {
			callback.onError("No internet connection!");
			return;
		}
		String imei = Utils.getUUID(CNPApplication.getInstance().getApplicationContext());
		String url = BASE_URL + "/who/?i="+ imei;
		
		try {
			new JSONRequestTask(new JSONRequestTaskHandler() {
				
				@Override
				public void onSuccess(JSONObject result) { 
					try {
						if(result.has("error")) {
							callback.onError(result.getString("error"));
						}
						callback.onSuccess(UserParser.parse(result.getJSONObject("user")));
						
					}catch(Exception e) {
						callback.onError(e.getMessage());
					}
				}
				
				@Override
				public void onError(String message) {
					callback.onError(message);
				}
				
			}).execute(url);
			
		}catch(Exception e) {
			callback.onError(e.getMessage());
		}
	}
	
	public void getPosts(int page, final PostsCallback callback) {
		
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
			// only for gingerbread and newer versions
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		if(!this.isConnected()) {
			callback.onError("No internet connection!");
			return;
		}
		User user = CNPApplication.getUser();
		String url = BASE_URL + "/get/?i="+ user.getImei();
		try {
			new JSONRequestTask(new JSONRequestTaskHandler() {
				
				@Override
				public void onSuccess(JSONObject result) { 
					try {
						if(result.has("error")) {
							callback.onError(result.getString("error"));
						}
						JSONArray _posts = result.getJSONArray("posts");
						callback.onSuccess(PostsParser.parse(_posts));
						
					}catch(Exception e) {
						callback.onError(e.getMessage());
					}
				}
				
				@Override
				public void onError(String message) {
					callback.onError(message);
				}
				
			}).execute(url);
			
		}catch(Exception e) {
			callback.onError(e.getMessage());
		}
	}
	
	private boolean isConnected() {
		return Utils.isConnected(CNPApplication.getInstance().getApplicationContext());
	}
}
