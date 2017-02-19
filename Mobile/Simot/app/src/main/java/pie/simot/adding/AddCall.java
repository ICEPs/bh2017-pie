package pie.simot.adding;



//import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import pie.simot.R;
import pie.simot.tabbedfragments.Dashboard;
import pie.simot.user.Login;

public class AddCall extends AppCompatActivity{

    private Button createPost;
    private EditText postTitle, callDesc;
    private Spinner urgency;

    //    @Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call);
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_call);
//
//        expiryDate = (Button)findViewById(R.id.expiryDate);
//
//        expiryDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar now = Calendar.getInstance();
//                DatePickerDialog dpd = DatePickerDialog.newInstance(
//                        AddCall.this,
//                        now.get(Calendar.YEAR),
//                        now.get(Calendar.MONTH),
//                        now.get(Calendar.DAY_OF_MONTH)
//                );
//
//                dpd.show(getFragmentManager(), "Datepickerdialog");
//
//            }
//        });
//
//    }

        createPost = (Button) findViewById(R.id.createPost);
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDesc = (EditText) findViewById(R.id.callDesc);
                postTitle = (EditText) findViewById(R.id.postName);
                urgency = (Spinner) findViewById(R.id.urgencyLevels);

                String desc = callDesc.getText().toString();
                String post = postTitle.getText().toString();
                int level = 0;
                String ur = urgency.getSelectedItem().toString();
                if (ur.contains("4")) level = 4;
                else if (ur.contains("3")) level = 3;
                else if (ur.contains("2")) level = 2;
                else if (ur.contains("1")) level = 1;

                if (!(desc.isEmpty() || post.isEmpty())) {

                }

            }
        });

        (findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(AddCall.this, Dashboard.class);
                AddCall.this.startActivity(next);
                finish();
            }
        });

    }
}
