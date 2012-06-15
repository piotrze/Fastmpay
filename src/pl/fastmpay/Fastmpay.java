package pl.fastmpay;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Fastmpay extends Activity {
	public static final String TICKET_20 = "*145*220020";
	public static final String TICKET_40 = "*145*220040";
	public static final String TICKET_60 = "*145*220060";
	public static final String TICKET_1DAY = "*145*221101";
	public static final String encodedHash = Uri.encode("#");
	SharedPreferences preferences;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Initialize preferences
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		if (preferences.getString("pin", "").length() == 0) {
			Toast.makeText(Fastmpay.this,
					"Nie masz ustawionego PIN'u, podaj go!", Toast.LENGTH_LONG)
					.show();
			startActivity(new Intent(Fastmpay.this, Settings.class));
		}
	}

	public void onClickBtnSend20(View button) {
		call(TICKET_20);
	}

	public void onClickBtnSend40(View button) {
		call(TICKET_40);
	}

	public void onClickBtnSend1Day(View button) {
		call(TICKET_1DAY);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			startActivity(new Intent(Fastmpay.this, Settings.class));
			return (true);
		default:
			return (false);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "Podaj hasło");
		return (super.onCreateOptionsMenu(menu));
	}

	private void call(String ticketType) {
		ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		clipboard.setText(preferences.getString("pin", "0000"));
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ ticketType + encodedHash));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}