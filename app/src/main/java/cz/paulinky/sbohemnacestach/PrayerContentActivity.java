package cz.paulinky.sbohemnacestach;

import static cz.paulinky.sbohemnacestach.TitleActivity.textSize;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrayerContentActivity extends AppCompatActivity {

    String prayerContent;
    String prayerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_content);

        Intent intent = getIntent();
        prayerName = intent.getStringExtra("name");
        prayerContent = intent.getStringExtra("content");

        TextView TVContent = (TextView) findViewById(R.id.content);
        TVContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        if (Build.VERSION.SDK_INT >= 24) {
            TVContent.setText(Html.fromHtml(prayerContent, Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
        } else {
            TVContent.setText(Html.fromHtml(prayerContent)); // or for older api
        }

        TVContent.bringToFront();

        setTitle(prayerName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_prayer_content, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        TextView TVContent = (TextView) findViewById(R.id.content);
        TVContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        SharedPreferences.Editor editor = prefs.edit();
//        TextView TVContent = (TextView) findViewById(R.id.content);

        int itemId = item.getItemId();
        if (itemId == R.id.action_share) {
            Intent send = new Intent(Intent.ACTION_SENDTO);

            prayerContent = prayerContent.replaceAll("<br>", "\\\n");
            prayerContent = prayerContent.replaceAll("\\<.*?>", "");

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, prayerContent);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            return true;
        } else if (itemId == R.id.action_about_app) {
            Intent myIntent = new Intent(PrayerContentActivity.this, AboutApp.class);
            PrayerContentActivity.this.startActivity(myIntent);
            return true;
        } else if (itemId == R.id.action_text_size_change) {
            Intent myIntent2 = new Intent(PrayerContentActivity.this, SeekbarActivity.class);
            PrayerContentActivity.this.startActivity(myIntent2);
            return true;
        } else if (itemId == R.id.action_content) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
