package pie.simot;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterAs extends DialogFragment {

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_register_as, container, false);

        view.findViewById(R.id.doneButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = -1;
                RadioGroup chooseType = (RadioGroup) view.findViewById(R.id.chooseType);
                index = chooseType.indexOfChild(view.findViewById(chooseType.getCheckedRadioButtonId()));
                if(index == -1){
                    Toast.makeText(getContext(), "Please choose a role", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences prefs = getActivity().getSharedPreferences(FinalsClass.PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putInt(FinalsClass.ROLE_TYPE, index);
                    edit.commit();

                    getActivity().startActivity(new Intent(getActivity(), Register.class));
                    getActivity().finish();
                    RegisterAs.this.dismiss();
                }

            }
        });
        //if index is 0 => Benefactor, 1 => Beneficiary
        return view;
    }

    public static void setSize(Activity act, DialogFragment dialog) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        Window d = dialog.getDialog().getWindow();
        d.setLayout(width - 10, height / 2);

    }
}