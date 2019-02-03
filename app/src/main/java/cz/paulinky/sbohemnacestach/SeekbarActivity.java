package cz.paulinky.sbohemnacestach;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static cz.paulinky.sbohemnacestach.R.id.testText;
import static cz.paulinky.sbohemnacestach.TitleActivity.prefs;
import static cz.paulinky.sbohemnacestach.TitleActivity.textSize;

public class SeekbarActivity extends AppCompatActivity {

    TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);

        setTitle(getResources().getString(R.string.change_text_size_title));

        //inicializace + nastavení hodnoty z Shared prefs
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(textSize);

        //inicializace + nastavení hodnoty z Shared prefs
        testText = (TextView) findViewById(R.id.testText);
        testText.setTextSize(textSize);

        //listener pro seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            SharedPreferences.Editor editor = prefs.edit();

            @Override
            //
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                testText.setTextSize(progresValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textSize = seekBar.getProgress();
                editor.putInt("textSize", textSize);
                editor.commit();
            }
        });

    }

// pro případ ikony save
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_seekbar, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//
//        switch (item.getItemId()) {
//
//            case R.id.action_save:
//                finish();
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//
//        }
//    }
}
