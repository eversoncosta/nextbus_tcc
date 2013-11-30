package pacote.NextBus10;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Act_SScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act__sscreen);
		
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				finish();
				
				Intent it_splash = new Intent();
				it_splash.setClass(Act_SScreen.this, Act_Principal.class);
				startActivity(it_splash);
				
			}
		}, 2000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act__sscreen, menu);
		return true;
	}

}
