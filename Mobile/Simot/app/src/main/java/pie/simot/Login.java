package pie.simot;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity{


    private String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText usernameInput = (EditText)findViewById(R.id.username);
        EditText passwordInput = (EditText)findViewById(R.id.password);

        username = usernameInput.getText().toString();
        password = passwordInput.getText().toString();
        Button loginButton = (Button)findViewById(R.id.loginButton);
        Button registerButton = (Button)findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //after authenticating
                Login.this.startActivity(new Intent(Login.this, Dashboard.class));
                Login.this.finish();
            }
        });
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterAs ask = new RegisterAs();
//                RegisterAs.setSize(Login.this, ask);
                ask.show(getSupportFragmentManager(), "ask_role");

            }
        });

    }
}

