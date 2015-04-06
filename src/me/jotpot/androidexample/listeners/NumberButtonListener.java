/**
 * 
 */
package me.jotpot.androidexample.listeners;

import me.jotpot.androidexample.Main;
import me.jotpot.androidexample.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Administrator
 *
 */
public class NumberButtonListener implements OnClickListener {

	private Main main;
	
	public NumberButtonListener(Main main){
		this.main = main;
	}
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		EditText editText = (EditText)main.findViewById(R.id.resultdisplay);
		Button btn = (Button)v;
		String content = editText.getText().toString();
		if (main.isOperatorClick || main.isCalcError){
			content = "";
		}
		if (content.length() ==21){
			return;
		}
		if (content.equals("0")){
			content = btn.getText().toString();
		} else if (content.contains("%")){
			content = content.substring(0,content.length()-1) + btn.getText() + "%";
		} else {
			content = content + btn.getText().toString();
		}
		
		if (content.length() >= 11){
			editText.setTextSize(25.0f);
		} else {
			editText.setTextSize(50.0f);
		}
		
		main.isOperatorClick = false;
		
		editText.setText(content);
	}

}
