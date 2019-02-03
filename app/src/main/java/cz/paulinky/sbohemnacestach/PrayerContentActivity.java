package cz.paulinky.sbohemnacestach;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.button;
import static android.R.attr.color;
import static android.R.attr.duration;
import static android.R.attr.id;
import static android.R.id.button2;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static cz.paulinky.sbohemnacestach.TitleActivity.prefs;
import static cz.paulinky.sbohemnacestach.TitleActivity.textSize;

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

        switch (item.getItemId()) {
            case R.id.action_share:
                Intent send = new Intent(Intent.ACTION_SENDTO);

                prayerContent = prayerContent.replaceAll("<br>","\\\n");
                prayerContent = prayerContent.replaceAll("\\<.*?>","");

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, prayerContent);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;


            case R.id.action_about_app:
                Intent myIntent = new Intent(PrayerContentActivity.this, AboutApp.class);
                PrayerContentActivity.this.startActivity(myIntent);
                return true;

            case R.id.action_text_size_change:
                Intent myIntent2 = new Intent(PrayerContentActivity.this, SeekbarActivity.class);
                PrayerContentActivity.this.startActivity(myIntent2);
                return true;

            case R.id.action_content:
                finish();
                return true;


            default:

                return super.onOptionsItemSelected(item);

        }
    }
}
