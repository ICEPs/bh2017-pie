package pie.simot.asynctasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import pie.simot.CheckInternet;
import pie.simot.tabbedfragments.Dashboard;

/**
 * Created by elysi on 2/18/2017.
 */

public class LoginAsync extends AsyncTask<Void, Void, String>{
    private String username, password;
    private Context c;
    private String loginLink = "http://utotcatalog.technotrekinc.com/z_login.php";
    //sample http://utotcatalog.technotrekinc.com/z_login.php?email=elysiajelenavilladarez@yahoo.com&password=luffy1
    private static ProgressDialog progressDialog;
    private Activity act;

    public LoginAsync(Context c, Activity act, String username, String password) {
        this.username = username;
        this.password = password;
        this.c = c;
        this.act = act;
        this.progressDialog = new ProgressDialog(c);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setTitle("Signing in . . .");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        if(CheckInternet.hasActiveInternetConnection(act)) {
            loginLink += "?email=" + username + "&password=" + password;

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            HttpResponse response;
            String json;
            JSONObject req = null;
            String success = "";

            try {
                request.setURI(new URI(loginLink));
                response = client.execute(request);
                json = EntityUtils.toString(response.getEntity());
                req = new JSONObject(json);
                success = req.getString("result");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (success.equals("success")) {
                try {
                    JSONArray alarm_list = req.getJSONArray("alarm_list");
                    for (int i = 0; i < alarm_list.length(); i++) {
                        JSONObject alarm = alarm_list.getJSONObject(i);
                    }

                    JSONArray hugot_list = req.getJSONArray("hugot_list");
                    for (int i = 0; i < hugot_list.length(); i++) {
                        JSONObject poem = hugot_list.getJSONObject(i);
                    }

                    JSONArray localhugot_list = req.getJSONArray("localhugot_list");
                    for (int i = 0; i < localhugot_list.length(); i++) {

                    }
//
//                System.out.println("CHECK: POEM ASYNC" + Realm.getDefaultInstance().where(Poem.class).count());

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            return success;
        } else return "";
    }

    @Override
    protected void onPostExecute(String result) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if(result.equals("success")){
            Toast.makeText(act, "Successfully logged in!", Toast.LENGTH_SHORT).show();
            Intent next = new Intent(act, Dashboard.class);
//            next.putExtra(FinalVariables.EMAIL, username);
            act.startActivity(next);
            act.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            act.finish();
        } else if(result.trim().isEmpty()){
        } else{
        }

    }
}
