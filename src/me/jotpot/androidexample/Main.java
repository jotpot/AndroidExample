package me.jotpot.androidexample;

import me.jotpot.androidexample.listeners.NumberButtonListener;
import me.jotpot.androidexample.listeners.OperatorButtonListener;
import me.jotpot.androidexample.listeners.SignalButtonListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class Main extends ActionBarActivity {

	private Integer[] buttonNums = { R.id.button0, R.id.button1, R.id.button2,
			R.id.button3, R.id.button4, R.id.button5, R.id.button6,
			R.id.button7, R.id.button8, R.id.button9 };

	private Integer[] buttonOperators = { R.id.buttonplus, R.id.buttonmulti,
			R.id.buttonsub, R.id.buttondivision, R.id.buttonequal,R.id.buttonac };

	private Integer[] buttonSignals = { R.id.buttonpercent, R.id.buttonpoint,
			R.id.buttonsignal };
	
	public Boolean isOperatorClick = false;
	
	public Boolean isCalcError = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setButtonListener();

	}

	private void setButtonListener() {

		for (Integer buttonNum : buttonNums){
			Button button = (Button)findViewById(buttonNum);
			button.setOnClickListener(new NumberButtonListener(this));
		}
		
		for (Integer buttonSignal : buttonSignals){
			Button button = (Button)findViewById(buttonSignal);
			button.setOnClickListener(new SignalButtonListener(this));
		}
		
		for (Integer buttonOperator : buttonOperators){
			Button button = (Button)findViewById(buttonOperator);
			button.setOnClickListener(new OperatorButtonListener(this));
		}
	}

	public void toastMake(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
			OperatorButtonListener.clearCache();
		}
		
		return super.onKeyDown(keyCode, event);
	}

}
