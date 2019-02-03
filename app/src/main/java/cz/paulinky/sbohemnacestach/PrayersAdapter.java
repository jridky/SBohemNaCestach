package cz.paulinky.sbohemnacestach;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static cz.paulinky.sbohemnacestach.TitleActivity.textSize;

/**
 * Created by ales on 10.2.17.
 */

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class PrayersAdapter extends
        RecyclerView.Adapter<PrayersAdapter.ViewHolder> {

    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_COLORED = 1;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row

        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            //numberTextView = (TextView) itemView.findViewById(R.id.item_number);
            nameTextView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    // Store a member variable for the prayers
    private List<Prayer> mPrayers;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public PrayersAdapter(Context context, List<Prayer> prayers) {
        mPrayers = prayers;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public PrayersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PrayersAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Prayer prayer = mPrayers.get(position);

        TextView textView2 = viewHolder.nameTextView;

        final int itemType = getItemViewType(position);

        if (itemType == ITEM_TYPE_NORMAL) {
            textView2.setBackgroundColor(Color.WHITE);
        } else if (itemType == ITEM_TYPE_COLORED){
//            textView2.setBackgroundColor(Color.rgb(232, 234, 246));
            textView2.setBackgroundColor(Color.rgb(227, 242, 253));
        }

        textView2.setText(prayer.getName());
        textView2.setTextSize(textSize);

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mPrayers.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2 == 0) {
            return ITEM_TYPE_NORMAL;
        } else {
            return ITEM_TYPE_COLORED;
        }
    }
}
