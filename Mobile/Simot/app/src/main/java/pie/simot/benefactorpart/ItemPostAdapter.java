package pie.simot.benefactorpart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pie.simot.R;

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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cardlayout_item, parent, false);
        }
        TextView itemName = (TextView)convertView.findViewById(R.id.nameOfItem);
        TextView benefactor = (TextView)convertView.findViewById(R.id.nameOfBenefactor);
        TextView urgency = (TextView)convertView.findViewById(R.id.itemUrgency);
        TextView description = (TextView)convertView.findViewById(R.id.itemDescription);

        itemName.setText(item.getItems());
        benefactor.setText(item.getCompanyName());
        urgency.setText(item.getUrgencyLevel());
        description.setText(item.getItemDesc());


        return convertView;
    }
}
