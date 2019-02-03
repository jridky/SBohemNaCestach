package cz.paulinky.sbohemnacestach;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;

/**
 * Created by ales on 9.2.17.
 */

public class Prayer {
    private String mNumber;
    private String mName;
    private String mContent;

    public Prayer(String number, String name, String Content) {
        mNumber = number;
        mName = name;
        mContent = Content;
    }

    public String getName() {
        return mName;
    }

    public String getNumber() {
        return mNumber;
    }

    public String getmContent() {
        return mContent;
    }

    public static ArrayList<Prayer> createListOfPrayers(Context ctx) {
        ArrayList<Prayer> prayers = new ArrayList<Prayer>();

        Resources res = ctx.getResources();
        String[] arrayOfPrayerNames = res.getStringArray(R.array.prayers_name);
        String[] arrayOfPrayerContents = res.getStringArray(R.array.prayers_content);

        for (int i = 1; i <= arrayOfPrayerNames.length; i++) {
            prayers.add(new Prayer("" + i, arrayOfPrayerNames[i-1], arrayOfPrayerContents[i-1]));
        }

        return prayers;
    }


}
