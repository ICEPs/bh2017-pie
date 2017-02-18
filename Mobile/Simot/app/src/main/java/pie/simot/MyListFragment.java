package pie.simot;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyListFragment extends Fragment {

    public MyListFragment() {
        // Required empty public constructor
    }

    public static MyListFragment newInstance(int roleType) {
        MyListFragment fragment = new MyListFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        args.putInt(FinalsClass.ROLE_TYPE, roleType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_my_list, container, false);
        int roleType = getArguments().getInt(FinalsClass.ROLE_TYPE);
        ListView listView = (ListView)view.findViewById(R.id.myListView);

        if(roleType == 0){
            ArrayList<Item> i = new ArrayList<>();
            i.add(new Item());
            i.add(new Item());
            i.add(new Item());
            i.add(new Item());
            i.add(new Item());
            i.add(new Item());
            i.add(new Item());
            i.add(new Item());
            i.add(new Item());

            ItemPostAdapter ita = new ItemPostAdapter(getContext(), i);
            listView.setAdapter(ita);
            ita.notifyDataSetChanged();
        } else if(roleType==1){
            ArrayList<Call> c = new ArrayList<>();
            c.add(new Call());
            c.add(new Call());
            c.add(new Call());
            c.add(new Call());
            c.add(new Call());
            c.add(new Call());
            c.add(new Call());
            c.add(new Call());

            CallByDonAdapter cbd = new CallByDonAdapter(getContext(), c);
            listView.setAdapter(cbd);
            cbd.notifyDataSetChanged();

        }else{
            Toast.makeText(getContext(), "RoleType: " + roleType, Toast.LENGTH_SHORT).show();
        }
        return view;
    }


}
