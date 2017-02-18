package pie.simot.tabbedfragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pie.simot.FinalsClass;
import pie.simot.R;
import pie.simot.benefactorpart.Item;

/**
 * Created by elysi on 2/19/2017.
 */

public class ItemClicked extends DialogFragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_item_click, container, false);
        SharedPreferences prefs = getActivity().getSharedPreferences(FinalsClass.PREFS_NAME, Context.MODE_PRIVATE);
        int roleType = prefs.getInt(FinalsClass.ROLE_TYPE, -1);
        Item item;
        if(roleType == 0){
            item = MyListFragment.item;
        } else if(roleType == 1){
            item = HostListFragment.item;
        }
        return view;
    }
}
