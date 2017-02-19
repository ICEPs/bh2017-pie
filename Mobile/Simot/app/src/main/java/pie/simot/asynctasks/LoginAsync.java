package pie.simot.asynctasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import pie.simot.CheckInternet;
import pie.simot.FinalsClass;
import pie.simot.tabbedfragments.Dashboard;

/**
 * Created by elysi on 2/18/2017.
 */

public class LoginAsync extends AsyncTask<Void, Void, String>{
    private String username, password;
    private Context c;
    private String loginLink = "http://e60a750c.ngrok.io/authenticate";
    //sample http://utotcatalog.technotrekinc.com/z_login.php?email=elysiajelenavilladarez@yahoo.com&password=luffy1
    private static ProgressDialog progressDialog;
    private Activity act;

    public LoginAsync(Context c, Activity act, String username, String password) {
        this.username = username;
        this.password = password;
        this.c = c;
        this.act = act;
//        this.progressDialog = new ProgressDialog(c);
    }

    @Override
    protected void onPreExecute() {
//        progressDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        progressDialog.setTitle("Signing in . . .");
//        progressDialog.setCancelable(false);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        if(CheckInternet.hasActiveInternetConnection(act)) {
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("email", username));
            urlParameters.add(new BasicNameValuePair("password", password));

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost();
            HttpResponse response;
            String json;
            JSONObject req = null;
            String success = "";

            try {
                post.setURI(new URI(loginLink));
                post.setEntity(new UrlEncodedFormEntity(urlParameters));
                response = client.execute(post);
                json = EntityUtils.toString(response.getEntity());
                req = new JSONObject(json);
                success = req.getString("auth_token");


                if (success!=null) {
                    SharedPreferences prefs = act.getSharedPreferences(FinalsClass.PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putString(FinalsClass.AUTHTOKEN, new JSONObject().getString("auth_token"));
                    edit.putString(FinalsClass.USERID, new JSONObject().getString("user_id"));
                    int roleType;
                    String role = new JSONObject().getString("type");
                    if(role.equals("Benefactor")){
                        roleType = 0;
                    }else{
                        roleType = 1;
                    }
                    edit.putInt(FinalsClass.ROLE_TYPE, roleType);
//
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return success;
        } else {
            return "";
        }
//            loginLink += "?email=" + username + "&password=" + password;
//
//            HttpClient client = new DefaultHttpClient();
//            HttpGet request = new HttpGet();
//            HttpResponse response;
//            String json;
//            JSONObject req = null;
//            String success = "";
//
//            try {
//                request.setURI(new URI(loginLink));
//                response = client.execute(request);
//                json = EntityUtils.toString(response.getEntity());
//                req = new JSONObject(json);
//                success = req.getString("auth_token");
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            } catch (ClientProtocolException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            if (success != null) {
//                try {
//                    SharedPreferences prefs = act.getSharedPreferences(FinalsClass.PREFS_NAME, Context.MODE_PRIVATE);
//                    SharedPreferences.Editor edit = prefs.edit();
//                    edit.putString(FinalsClass.AUTHTOKEN, new JSONObject().getString("auth_token"));
//                    edit.putString(FinalsClass.USERID, new JSONObject().getString("user_id"));
////
////                System.out.println("CHECK: POEM ASYNC" + Realm.getDefaultInstance().where(Poem.class).count());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            return success;
//        } else return "";
    }

    @Override
    protected void onPostExecute(String result) {
//        if (progressDialog.isShowing()) {
//            progressDialog.dismiss();
//        }
        if(!result.isEmpty()){
            Toast.makeText(act, "Successfully logged in!", Toast.LENGTH_SHORT).show();
            SharedPreferences prefs = act.getSharedPreferences(FinalsClass.PREFS_NAME, Context.MODE_PRIVATE);
            int roleType = prefs.getInt(FinalsClass.ROLE_TYPE, 0);
            if(roleType == 1) {
                GetAllCallsTask gat = new GetAllCallsTask(c, act);
                gat.execute();
            } else{
//                GetAllItemsTask gat = new GetAllItemsTask(c, act);
//                gat.execute();
            }
        } else if(result.trim().isEmpty()){
            Toast.makeText(c, "Error: Invalid Credentials", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(c, "Error: Invalid Credentials", Toast.LENGTH_SHORT).show();
        }

    }
}
