/**
 * 
 */
package me.jotpot.androidexample.listeners;

import java.math.BigDecimal;

import me.jotpot.androidexample.Main;
import me.jotpot.androidexample.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Administrator
 *
 */
public class OperatorButtonListener implements OnClickListener {

	private Main main;

	private static String formerNumber;

	private static Integer operator;

	public OperatorButtonListener(Main main) {

		this.main = main;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		Button btn = (Button) v;

		EditText editText = (EditText) main.findViewById(R.id.resultdisplay);
		if (btn.getId() == R.id.buttonac) {
			editText.setText("0");
			return;
		} else {
			
			if (formerNumber != null && !main.isOperatorClick) {
				// ¼ÆËã½á¹û
				String currentNumber = editText.getText().toString();
				BigDecimal firstNumber = stringToNum(formerNumber);
				BigDecimal secondNumber = stringToNum(currentNumber);
				if (secondNumber.equals(BigDecimal.ZERO) && operator == R.id.buttondivision){
					editText.setTextSize(25.0f);
					editText.setText("ERROR:DIVID ZERO!");
					main.isOperatorClick = false;
					main.isCalcError = true;
					operator = null;
					formerNumber = "0";
					return;
				}
				BigDecimal result = calc(firstNumber, secondNumber, operator);
				String resultStr = result.toPlainString();
				if (resultStr.length() >= 11){
					editText.setTextSize(25.0f);
				}
				if (resultStr.length() >21){
					editText.setTextSize(25.0f);
					editText.setText("ERROR:OVERFLOW,MAX LENGTH 21");
					main.isOperatorClick = false;
					main.isCalcError = true;
					operator = null;
					formerNumber = "0";
					return;
				}
				editText.setText(result.toPlainString());
			}
			if (btn.getId() != R.id.buttonequal){
				operator = btn.getId();
			}
			formerNumber = editText.getText().toString();
			
			main.isOperatorClick = true;
		}
	}

	private BigDecimal stringToNum(String string) {
		BigDecimal result = BigDecimal.ZERO;
		if (string.contains("%")) {
			result = new BigDecimal(string.substring(0, string.length() - 1));
			result = result.divide(new BigDecimal(100));
		} else {
			result = new BigDecimal(string);
		}
		return result;
	}

	private BigDecimal calc(BigDecimal firstNumber, BigDecimal secondNumber,
			Integer operator) {
		BigDecimal result = BigDecimal.ZERO;
		switch (operator) {
		case R.id.buttonplus:
			result = firstNumber.add(secondNumber);
			break;
		case R.id.buttonsub:
			result = firstNumber.subtract(secondNumber);
			break;
		case R.id.buttonmulti:
			result = firstNumber.multiply(secondNumber);
			break;
		case R.id.buttondivision:
			if (secondNumber.equals(BigDecimal.ZERO)){
				
			} else {
				result = firstNumber.divide(secondNumber);
			}
		}
		return result;
	}
	
	public static void clearCache(){
		formerNumber = null;
		operator = null;
	}

}
