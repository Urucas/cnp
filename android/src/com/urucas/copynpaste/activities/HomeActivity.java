package com.urucas.copynpaste.activities;

import java.util.ArrayList;

import com.urucas.copynpaste.CNPApplication;
import com.urucas.copynpaste.R;
import com.urucas.copynpaste.adapters.PostsAdapter;
import com.urucas.copynpaste.callbacks.PostsCallback;
import com.urucas.copynpaste.model.Post;
import com.urucas.copynpaste.model.User;
import com.urucas.utils.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class HomeActivity extends Activity {

	private PostsAdapter _adapter;
	private ListView _postslist;
	private int page = 0;
	private AdView adView;
	private TextView urlTextView;
	private ImageButton updateButton;
	private ProgressBar progressBar1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		_adapter = new PostsAdapter(HomeActivity.this, R.layout.adapter_post_item, new ArrayList<Post>());

		_postslist = (ListView) findViewById(R.id.postsListView);
		_postslist.setAdapter(_adapter);
		_postslist.setOnItemClickListener(new OnPostClickListener());

		// Create the adView.
		AdRequest adRequest = new AdRequest();
		
		User user = CNPApplication.getUser();
		urlTextView = (TextView) findViewById(R.id.urlTextView);
		urlTextView.setText("http://cnp.urucas.com/?c="+user.getCode());
		
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar1.setVisibility(View.VISIBLE);
		
		updateButton = (ImageButton) findViewById(R.id.updateButton);
		updateButton.setVisibility(View.GONE);
		updateButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getUserPosts(1);
			}
		});
		getUserPosts(1);
	}

	private class OnPostClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapter, View parent, int position,
				long id) {

			Post p = (Post) adapter.getItemAtPosition(position);
			copyToClipboard(p.getData());
		}
	}

	private void copyToClipboard(String data) {

		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if(currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB) {
			ClipboardManager clipboard = (ClipboardManager) getApplicationContext().getSystemService(CLIPBOARD_SERVICE); 
			ClipData clip = ClipData.newPlainText("posts text", data);
			clipboard.setPrimaryClip(clip);
		}
		else {
			android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getApplicationContext().getSystemService(CLIPBOARD_SERVICE);
			clipboard.setText(data);
		}
		Utils.Toast(HomeActivity.this, "Text copied to clipboard");
	}

	private void getUserPosts(int page) {
		progressBar1.setVisibility(View.VISIBLE);
		updateButton.setVisibility(View.GONE);
		CNPApplication.getAPIController().getPosts(page, new PostsCallback(){

			@Override
			public void onSuccess(ArrayList<Post> posts) {
				getPostsSuccess(posts);
			}

			@Override
			public void onError(String message) {
				getPostsError(message);
				
			}
		});
	}

	@Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
	
	private void getPostsError(String message) {
		progressBar1.setVisibility(View.GONE);
		updateButton.setVisibility(View.VISIBLE);
		
		Utils.Toast(HomeActivity.this, message);
	}
	
	private void getPostsSuccess(ArrayList<Post> posts) {
		progressBar1.setVisibility(View.GONE);
		updateButton.setVisibility(View.VISIBLE);
		
		_adapter.clear();
		_adapter.addAll(posts);
		_adapter.notifyDataSetChanged();
	}
}
