package pie.simot.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import pie.simot.tabbedfragments.Dashboard;
import pie.simot.FinalsClass;
import pie.simot.R;

public class Register extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks{

    String orgName, password, confirmPassword, address, repName, contactInfo, orgDesc;
    private PlaceAutoCompleteAdapter mAdapter;
    private static final LatLngBounds BOUNDS_JAMAICA= new LatLngBounds(new LatLng(-57.965341647205726, 144.9987719580531),
            new LatLng(72.77492067739843, -9.998857788741589));
    protected GoogleApiClient mGoogleApiClient;
    protected LatLng start;
    protected LatLng end;
    private String LIST_OF_LATLNG_QUERY = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText orgNameT = (EditText)findViewById(R.id.name);
        final EditText passwordT = (EditText)findViewById(R.id.password);
        EditText confirmPasswordT = (EditText)findViewById(R.id.confirmPassword);
        AutoCompleteTextView addressT = (AutoCompleteTextView)findViewById(R.id.address);
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

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        MapsInitializer.initialize(this);
        mGoogleApiClient.connect();

        mAdapter = new PlaceAutoCompleteAdapter(this, android.R.layout.simple_list_item_1,
                mGoogleApiClient, BOUNDS_JAMAICA, null);

        addressT.setAdapter(mAdapter);
        addressT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final PlaceAutoCompleteAdapter.PlaceAutocomplete item = mAdapter.getItem(position);
                final String placeId = String.valueOf(item.placeId);
//                Log.i(LOG_TAG, "Autocomplete item selected: " + item.description);

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
              details about the place.
              */
                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                        .getPlaceById(mGoogleApiClient, placeId);
                placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(PlaceBuffer places) {
                        if (!places.getStatus().isSuccess()) {
                            // Request did not complete successfully
//                            Log.e(LOG_TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                            places.release();
                            return;
                        }
                        // Get the Place object from the buffer.
                        final Place place = places.get(0);

                        start=place.getLatLng();
//                        latitude.add(start.latitude);
//                        longitude.add(start.longitude);
//                        Log.e("GeoData", parseData(place.getLatLng() + ""));
                        LIST_OF_LATLNG_QUERY = LIST_OF_LATLNG_QUERY +"origin=" + parseData(place.getLatLng() + "") + "&destination=" + parseData(place.getLatLng() + "") + "&";

                    }

                });
            }
        });

        Button registerButton = (Button)findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.equals(confirmPassword)){
                    SharedPreferences prefs = getSharedPreferences(FinalsClass.PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putString(FinalsClass.LATLNG, LIST_OF_LATLNG_QUERY);
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

    private String parseData(String LatLng){
        String[] part1 = LatLng.split("\\(");
        String part2[] = part1[1].split("\\)");
        return part2[0];
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

