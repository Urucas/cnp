package com.urucas.copynpaste;
import com.urucas.copynpaste.controllers.ApiController;
import com.urucas.copynpaste.model.User;
import android.app.Application;

public class CNPApplication extends Application {

	private static CNPApplication _instance;
	private static ApiController _apicontroller;
	private static User _user;
	
	public CNPApplication() {
		super();
		_instance = this;
	}
	
	public static User getUser() {
		return _user;
	}
	
	public static void setUser(User user){
		_user = user;
	}
	
	public static CNPApplication getInstance() {
		return _instance;
	}
	
	public static ApiController getAPIController() {
		if(_apicontroller == null) {
			_apicontroller = new ApiController();
		}
		return _apicontroller;
	}
}
