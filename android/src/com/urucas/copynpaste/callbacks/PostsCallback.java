package com.urucas.copynpaste.callbacks;

import java.util.ArrayList;
import com.urucas.copynpaste.model.Post;

public interface PostsCallback {

	public void onSuccess(ArrayList<Post> posts);
	public void onError(String message);
}
