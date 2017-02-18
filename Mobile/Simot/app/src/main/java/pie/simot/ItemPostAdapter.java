package pie.simot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by elysi on 2/18/2017.
 */

public class ItemPostAdapter extends ArrayAdapter<Item> {
    public ItemPostAdapter(Context context, ArrayList<Item> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Item item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cardlayout_call_for_donation, parent, false);
        }
        TextView itemName = (TextView)convertView.findViewById(R.id.nameOfItem);
        itemName.setText("MY HEART!!!");
        return convertView;
    }
}
