package pie.simot.asynctasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import pie.simot.beneficiarypart.Call;
import pie.simot.tabbedfragments.Dashboard;

/**
 * Created by elysi on 2/18/2017.
 */

public class GetAllCallsTask extends AsyncTask<Void, Void, ArrayList<Call>> {
    private Context c;
    private String donations = "http://65fb3151.ngrok.io/donations";
    //sample http://utotcatalog.technotrekinc.com/z_login.php?email=elysiajelenavilladarez@yahoo.com&password=luffy1
    private static ProgressDialog progressDialog;
    private Activity act;

    public GetAllCallsTask(Context c, Activity act) {
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
    protected ArrayList<Call> doInBackground(Void... params) {

        SharedPreferences prefs = act.getSharedPreferences(FinalsClass.PREFS_NAME, Context.MODE_PRIVATE);
        String auth = prefs.getString(FinalsClass.AUTHTOKEN, "");
        ArrayList<Call> c = new ArrayList<>();

        if(CheckInternet.hasActiveInternetConnection(act)) {

            HttpClient client = new DefaultHttpClient();

            HttpGet request = new HttpGet();
            request.setHeader("Authorization", auth);
            HttpResponse response;
            String json;
            JSONArray req = null;
            String success = "";

            try {
                request.setURI(new URI(donations));
                response = client.execute(request);
                json = EntityUtils.toString(response.getEntity());
                req = new JSONArray(json);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (req != null) {
                try {
                    for(int i = 0; i < req.length(); i++){
                        JSONObject d = req.getJSONObject(i);
                        Call call = new Call();
                        call.setOrgName(d.getString("organization_name"));
                        call.setMessageBody(d.getString("message"));
                        call.setUrgencyLevel(d.getInt("urgency"));

                        c.add(call);
                    }
//
//                System.out.println("CHECK: POEM ASYNC" + Realm.getDefaultInstance().where(Poem.class).count());

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            return c;
        } else return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Call> result) {
//        if (progressDialog.isShowing()) {
//            progressDialog.dismiss();
//        }
        if(result != null && !result.isEmpty()){
            Intent next = new Intent(act, Dashboard.class);
            next.putParcelableArrayListExtra(FinalsClass.PARCEDON, result);
            act.startActivity(next);
            act.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            act.finish();
        } else if(result.isEmpty() || result == null){
            Toast.makeText(c, "Error", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(c, "Error else", Toast.LENGTH_SHORT).show();
        }

    }
}
