package com.urucas.copynpaste.callbacks;

import java.util.ArrayList;
import com.urucas.copynpaste.model.User;

public interface UserCallback {

	public void onSuccess(User user);
	public void onError(String message);
}
