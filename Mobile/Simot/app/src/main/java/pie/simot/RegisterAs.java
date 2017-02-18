package pie.simot;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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

public class RegisterAs extends DialogFragment {

    static RegisterAs newInstance() {
        return new RegisterAs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_register_as, container, false);
        RadioGroup chooseType = (RadioGroup) v.findViewById(R.id.chooseType);
        int index = chooseType.indexOfChild(v.findViewById(chooseType.getCheckedRadioButtonId()));
        SharedPreferences prefs = getActivity().getSharedPreferences(FinalsClass.PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(FinalsClass.ROLE_TYPE, index);
        prefs.edit().commit();

        ((Button)v.findViewById(R.id.doneButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        //if index is 0 => Benefactor, 1 => Beneficiary
        return v;
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