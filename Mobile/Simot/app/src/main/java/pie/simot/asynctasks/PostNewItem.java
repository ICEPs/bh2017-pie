package pie.simot.asynctasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Window;
import android.view.WindowManager;

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
import pie.simot.tabbedfragments.Dashboard;

/**
 * Created by elysi on 2/19/2017.
 */

public class PostNewItem extends AsyncTask<Void, Void, String> {
private String latlng, orgName, password, address, repName, contactInfo, orgDesc;
private Context c;
private String registerLink = "http://utotcatalog.technotrekinc.com/z_registration.php";
private static ProgressDialog progressDialog;
private Activity act;

public PostNewItem(Context c, Activity act, String latlng, String orgName, String password,
        String address, String repName, String contactInfo, String orgDesc) {
        this.latlng = latlng;
        this.orgName = orgName;
        this.password = password;
        this.address = address;
        this.repName = repName;
        this.contactInfo = contactInfo;
        this.orgDesc = orgDesc;
        this.act = act;
        this.progressDialog = new ProgressDialog(c);
        }

@Override
protected void onPreExecute() {
        progressDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setTitle("Creating account . . .");
        progressDialog.setMessage("Please make sure you have a stable internet connection");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        }

@Override
protected String doInBackground(Void... params) {
        if (CheckInternet.hasActiveInternetConnection(act)) {
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("fbId", latlng));
        urlParameters.add(new BasicNameValuePair("email", orgName));
        urlParameters.add(new BasicNameValuePair("password", password));
        urlParameters.add(new BasicNameValuePair("fname", address));
        urlParameters.add(new BasicNameValuePair("lname", repName));
        urlParameters.add(new BasicNameValuePair("lname", contactInfo));
        urlParameters.add(new BasicNameValuePair("lname", orgDesc));
//        urlParameters.add(new BasicNameValuePair("deviceToken", "12345"));

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost();
        HttpResponse response;
        String json;
        JSONObject req = null;
        String success = "";

        try {
        post.setURI(new URI(registerLink));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        response = client.execute(post);
        json = EntityUtils.toString(response.getEntity());
        req = new JSONObject(json);
        success = req.getString("result");


        if (success.equals("success")) {

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
        }

@Override
protected void onPostExecute(String result) {
        if (progressDialog.isShowing()) {
        progressDialog.dismiss();
        }
        if (result.equals("success")) {

        Intent next = new Intent(act, Dashboard.class);
//            next.putExtra(FinalVariables.EMAIL, username);
        act.startActivity(next);
        act.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        act.finish();
        } else if (result.trim().isEmpty()) {
        } else {

        }

        }
        }
