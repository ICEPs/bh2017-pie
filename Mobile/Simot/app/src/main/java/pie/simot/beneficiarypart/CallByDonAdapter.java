package pie.simot.beneficiarypart;

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

public class CallByDonAdapter extends ArrayAdapter<Call> {
        public CallByDonAdapter(Context context, ArrayList<Call> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Call call = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.cardlayout_call_for_donation, parent, false);
            }
            TextView donCall = (TextView)convertView.findViewById(R.id.donationCall);
            donCall.setText("HELP PLS!!!");
            return convertView;
        }
    }

