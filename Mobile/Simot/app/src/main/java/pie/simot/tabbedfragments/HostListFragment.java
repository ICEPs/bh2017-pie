package pie.simot.tabbedfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import pie.simot.FinalsClass;
import pie.simot.R;
import pie.simot.benefactorpart.Item;
import pie.simot.benefactorpart.ItemPostAdapter;
import pie.simot.beneficiarypart.Call;
import pie.simot.beneficiarypart.CallByDonAdapter;

public class HostListFragment extends Fragment {
    public static Item item;
    public static Call call;

    private ArrayList<Item> i;
    private  ArrayList<Call> c;

    public HostListFragment() {
        // Required empty public constructor
    }

    public static HostListFragment newInstance(int roleType) {
        HostListFragment fragment = new HostListFragment();
        Bundle args = new Bundle();
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
         view = inflater.inflate(R.layout.fragment_host_list, container, false);
        int roleType = getArguments().getInt(FinalsClass.ROLE_TYPE);
        ListView listView = (ListView)view.findViewById(R.id.hostListView);
        if(roleType == 0){
            c = new ArrayList<>();
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

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    call = c.get(position);
                    ItemClicked clicked = new ItemClicked();
                    clicked.show(getActivity().getSupportFragmentManager(), "call_clicked");
                }
            });


        } else if(roleType == 1){
            i = new ArrayList<>();
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

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    item = i.get(position);
                    ItemClicked clicked = new ItemClicked();
                    clicked.show(getActivity().getSupportFragmentManager(), "item_clicked");
                }
            });
        } else{
            Toast.makeText(getContext(), "RoleType: " + roleType, Toast.LENGTH_SHORT).show();
        }

        return view;
    }

}
