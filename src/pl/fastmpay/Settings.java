package pl.fastmpay;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Settings extends PreferenceActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.settings);
		addPreferencesFromResource(R.xml.preferences);
	}
	
	public void onClickSavePassword(View button){
		EditText pin = (EditText)findViewById(R.id.password);
		Log.i("pin", pin.getText().toString());
	}

}
