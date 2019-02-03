package cz.paulinky.sbohemnacestach;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.R.attr.text;
import static android.R.attr.value;

public class TitleActivity extends AppCompatActivity {

    public static int textSize;
    public static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        //otevřu preference a načtu velikost textu z minula
        //pak už znova nemusím shared prefs otevírat, ale pracuji s proměnnou textSize
        prefs = getSharedPreferences("cz.paulinky.sbohemnacestach", Context.MODE_PRIVATE);
        textSize = prefs.getInt("textSize", 20);

    }

    public void skip(View view) {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
        finish();
    }
}
