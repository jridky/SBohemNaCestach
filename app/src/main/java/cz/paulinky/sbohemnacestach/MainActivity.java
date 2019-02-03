package cz.paulinky.sbohemnacestach;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.value;
import static cz.paulinky.sbohemnacestach.TitleActivity.prefs;
import static cz.paulinky.sbohemnacestach.TitleActivity.textSize;

public class MainActivity extends AppCompatActivity {

    ArrayList<Prayer> prayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize prayers
        prayers = Prayer.createListOfPrayers(this);

        // Lookup the recyclerview in activity layout
        RecyclerView RVPrayers = (RecyclerView) findViewById(R.id.my_recycler_view);
//        RVPrayers.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RVPrayers.addItemDecoration(itemDecoration);

        ItemClickSupport.addTo(RVPrayers).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // do it
                        Intent myIntent = new Intent(MainActivity.this, PrayerContentActivity.class);
                        myIntent.putExtra("name", prayers.get(position).getName()); //Optional parameters
                        myIntent.putExtra("content", prayers.get(position).getmContent()); //Optional parameters
                        MainActivity.this.startActivity(myIntent);
                    }
                }
        );


        // Create adapter passing in the sample user data
        PrayersAdapter adapter = new PrayersAdapter(this, prayers);
        // Attach the adapter to the recyclerview to populate items
        RVPrayers.setAdapter(adapter);
        // Set layout manager to position the items
        RVPrayers.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

    }

    @Override
    public void onResume() {
        super.onResume();


        //nutno aktualizovat RecyclerView, kvůli změnám velikosti písma
        RecyclerView RVPrayers = (RecyclerView) findViewById(R.id.my_recycler_view);
        PrayersAdapter adapter = new PrayersAdapter(this, prayers);

        RVPrayers.setAdapter(null);
        RVPrayers.setLayoutManager(null);
        RVPrayers.setAdapter(adapter);
        RVPrayers.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.action_text_size_change:
                Intent myIntent2 = new Intent(MainActivity.this, SeekbarActivity.class);
                MainActivity.this.startActivity(myIntent2);
                return true;

            case R.id.action_about_app:
                Intent myIntent = new Intent(MainActivity.this, AboutApp.class);
                MainActivity.this.startActivity(myIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
