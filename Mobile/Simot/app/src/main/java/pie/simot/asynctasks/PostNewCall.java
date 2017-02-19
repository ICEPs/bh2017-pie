package pie.simot.asynctasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import pie.simot.CheckInternet;
import pie.simot.FinalsClass;

/**
 * Created by elysi on 2/19/2017.
 */
//POST /donations
//
//        Parameters: message (string) organization_name (string) urgency (int)
public class PostNewCall extends AsyncTask<Void, Void, String>{
    private String message, orgName;
    private int urg;
        private Context c;
        private String loginLink = "http://65fb3151.ngrok.io/donations";
        //sample http://utotcatalog.technotrekinc.com/z_login.php?email=elysiajelenavilladarez@yahoo.com&password=luffy1
        private static ProgressDialog progressDialog;
        private Activity act;

        public PostNewCall(Context c, Activity act, String message, String orgName, int urg) {
            this.message = message;
            this.orgName = orgName;
            this.urg = urg;
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
                SharedPreferences prefs = act.getSharedPreferences(FinalsClass.PREFS_NAME, Context.MODE_PRIVATE);
                String auth = prefs.getString(FinalsClass.AUTHTOKEN, "");


                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                urlParameters.add(new BasicNameValuePair("message", message));
                urlParameters.add(new BasicNameValuePair("organization_name", orgName));
                urlParameters.add(new BasicNameValuePair("urgency", Integer.toString(urg)));

                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost();

                post.setHeader("Authorization", auth);
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
                Toast.makeText(act, "New call poster", Toast.LENGTH_SHORT).show();
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


