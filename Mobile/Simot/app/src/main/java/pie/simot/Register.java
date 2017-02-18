package pie.simot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    String orgName, password, confirmPassword, address, repName, contactInfo, orgDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText orgNameT = (EditText)findViewById(R.id.name);
        final EditText passwordT = (EditText)findViewById(R.id.password);
        EditText confirmPasswordT = (EditText)findViewById(R.id.confirmPassword);
        EditText addressT = (EditText)findViewById(R.id.address);
        EditText repNameT = (EditText)findViewById(R.id.representativeName);
        EditText contactInfoT = (EditText)findViewById(R.id.contactInfo);
        EditText orgDescT = (EditText)findViewById(R.id.orgDescription);

        orgName = orgNameT.getText().toString();
        password = passwordT.getText().toString();
        confirmPassword = confirmPasswordT.getText().toString();
        address = addressT.getText().toString();
        repName = repNameT.getText().toString();
        contactInfo = contactInfoT.getText().toString();
        orgDesc = orgDescT.getText().toString();

        Button registerButton = (Button)findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.equals(confirmPassword)){
                    SharedPreferences prefs = getSharedPreferences(FinalsClass.PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putString(FinalsClass.ORG_NAME, orgName);
                    edit.putString(FinalsClass.PASSWORD, password);
                    edit.putString(FinalsClass.ADDRESS, address);
                    edit.putString(FinalsClass.REPNAME, repName);
                    edit.putString(FinalsClass.CONTACT_INFO, contactInfo);
                    edit.putString(FinalsClass.ORG_DESC, orgDesc);
                    edit.commit();

                    //after storing to database the info + role type

                    startActivity(new Intent(Register.this, Dashboard.class));
                    Register.this.finish();

                } else{
                    Toast.makeText(getApplicationContext(), "Password and Confirm Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
