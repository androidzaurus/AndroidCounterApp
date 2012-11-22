package com.example.android.testcounter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestCounterActivity extends Activity {
	private int mCounter;
	private final String strPrefName = "testCounter";
	private final String strCount = "count";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
	protected void onResume() {
		super.onResume();
		SharedPreferences sp = getSharedPreferences(strPrefName, MODE_PRIVATE);
		mCounter = sp.getInt(strCount, 0);
		update();
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences sp = getSharedPreferences(strPrefName, MODE_PRIVATE);
		SharedPreferences.Editor e = sp.edit();
		e.putInt(strCount, mCounter);
		e.commit();
	}

	public void plus(View view) {
		mCounter++;
		update();
	}

	public void minus(View view) {
		if (mCounter > 0) {
			mCounter--;
			update();
		}
	}

	public void clear(View view) {
		mCounter = 0;
		update();
	}

	private void update() {
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText(String.valueOf(mCounter));
	}
}