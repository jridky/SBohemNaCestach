package cz.paulinky.sbohemnacestach;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.widget.TextView;

import org.w3c.dom.Text;

import static cz.paulinky.sbohemnacestach.TitleActivity.textSize;

public class AboutApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        // text v action baru
        setTitle(getResources().getString(R.string.about_app));

        TextView TVabout1 = (TextView) findViewById(R.id.about_app_text1);
        TVabout1.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        if (Build.VERSION.SDK_INT >= 24) {
            TVabout1.setText(Html.fromHtml(getResources().getString(R.string.about_app_content), Html.FROM_HTML_MODE_LEGACY)); // for 24 api and more
        } else {
            TVabout1.setText(Html.fromHtml(getResources().getString(R.string.about_app_content))); // or for older api
        }

        TVabout1.setMovementMethod((LinkMovementMethod.getInstance()));
        TVabout1.bringToFront();
    }
}
