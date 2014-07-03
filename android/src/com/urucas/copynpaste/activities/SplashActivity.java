package com.urucas.copynpaste.activities;

import com.crashlytics.android.Crashlytics;
import java.util.ArrayList;

import com.urucas.copynpaste.CNPApplication;
import com.urucas.copynpaste.R;
import com.urucas.copynpaste.adapters.PostsAdapter;
import com.urucas.copynpaste.callbacks.PostsCallback;
import com.urucas.copynpaste.callbacks.UserCallback;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class SplashActivity extends Activity {

	private ProgressBar progressBar1;
	private TextView loadingTextView;
	private TextView urlTextView;
	private Button startButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Crashlytics.start(this);
		setContentView(R.layout.activity_splash);
		
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		loadingTextView = (TextView) findViewById(R.id.loadingTextView);
		urlTextView = (TextView) findViewById(R.id.urlTextView);
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
				startActivity(intent);
			}
		});
		
		CNPApplication.getAPIController().getCode(new UserCallback() {
			
			@Override
			public void onSuccess(User user) {
				initApp(user);
			}
			
			@Override
			public void onError(String message) {
				Utils.Toast(SplashActivity.this, message);
			}
		});
	}

	private void initApp(User user) {
		CNPApplication.setUser(user);
		progressBar1.setVisibility(View.GONE);
		loadingTextView.setVisibility(View.GONE);
		startButton.setVisibility(View.VISIBLE);
		urlTextView.setText("http://cnp.urucas.com/?c="+user.getCode());
	}
}
