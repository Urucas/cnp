package com.urucas.copynpaste.adapters;

import java.util.ArrayList;

import com.urucas.copynpaste.model.Post;
import com.urucas.copynpaste.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PostsAdapter extends ArrayAdapter<Post>{

	public PostsAdapter(Context context, int resource, ArrayList<Post> posts) {
		super(context, resource, posts);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) this.getContext()
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		Post _post = getItem(position);
		
		View rowView = inflater.inflate(R.layout.adapter_post_item, parent, false);
		TextView data = (TextView) rowView.findViewById(R.id.postData);
				 data.setText(_post.getData());
				 
		return rowView;
	}
	
	public void addAll(ArrayList<Post> _posts) {
		for(int i=0; i<_posts.size();i++) {
			Post post = _posts.get(i);
			Log.i("post data", post.getData());
			this.insert(post, this.getCount());
		}
	}
}
