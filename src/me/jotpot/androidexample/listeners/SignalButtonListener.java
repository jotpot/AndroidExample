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
public class SignalButtonListener implements OnClickListener {

	private Main main;
	
	public SignalButtonListener(Main main) {
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
		if (!content.matches("^(-?\\d+)(\\.\\d+)?%?")){
			return;
		}
		if (btn.getId() == R.id.buttonpoint){
			if (content.contains(".")){
				return;
			} else {
				if (content.contains("%")){
					content = content.substring(0,content.length()-1) + ".%";
				} else {
					content = content + ".";
				}
			}
		} else if (btn.getId() == R.id.buttonpercent) {
			if (content.contains("%") || content.equals("0")){
				return;
			} else {
				content = content  + "%";
			}
			
		} else if (btn.getId() == R.id.buttonsignal){
			if (content.equals("0")){
				return;
			} else {
				if (content.contains("-")){
					content = content.substring(1);
				} else {
					content = "-" + content;
				}
			}
		}
		
		editText.setText(content);

	}

}
