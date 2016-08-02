package cz.uhk.cityuhelper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;

import cz.uhk.cityuhelper.model.EnumStringConverter;
import cz.uhk.cityuhelper.model.Item;

public class DetailActivity extends AppCompatActivity {

    private MapView mapView;
    private GoogleMap map;

    private String email;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Item item = StorageManager.loadObject(getApplicationContext(),getIntent().getExtras().getString("id"));

        if(item != null){
            //TODO fill those components :)
            //((TextView)findViewById(R.id.txtDetailType)).setText(item.getType().toString());

            setTitle(new EnumStringConverter(getApplicationContext()).enumToString(item.getType()));
            ((TextView)findViewById(R.id.txtDetailName)).setText(item.getAuthor().getName().toString());
            ((TextView)findViewById(R.id.txtDetailSubject)).setText(item.getTitle().toString());
            ((TextView)findViewById(R.id.txtDetailDescription)).setText(item.getDescription().toString());
            ((TextView)findViewById(R.id.txtDetailPhone)).setText(item.getAuthor().getTelephone().toString());
            ((TextView)findViewById(R.id.txtDetailEmail)).setText(item.getAuthor().getEmail().toString());

            if(!item.getAuthor().getEmail().isEmpty())
                email = item.getAuthor().getEmail().trim();

            if(!item.getAuthor().getTelephone().isEmpty())
                phoneNumber = item.getAuthor().getTelephone().trim();

            if(item.getPosition() != null){
                //add gps coordinates and so on :)
                //TODO KAI YOU CAN DO IT!
                String s = "Gps: "+item.getPosition().latitude+", "+item.getPosition().longitude;
                ((TextView)findViewById(R.id.txtDetailPosition)).setText(s);
            }else{
                //DO NOTHING
            }
        }

        if(item.getPosition() != null){
            //MAPVIEW INITIALIZE
            mapView = (MapView) findViewById(R.id.mapView);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    map = googleMap;
                    map.getUiSettings().setAllGesturesEnabled(false);
                    map.getUiSettings().setZoomControlsEnabled(true);
                    map.addMarker(new MarkerOptions().position(item.getPosition().getLatLng())
                            .title(item.getTitle())).showInfoWindow();
                    map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                            .target(item.getPosition().getLatLng()).zoom(17.0f).build()));
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            setResult(MainActivity.ITEMADDEDABORTED);
            this.finish();
            return true;
        }
        if (id == R.id.action_sendmail){

            if(email != null){
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Reply");
                intent.putExtra(Intent.EXTRA_TEXT, "Hello,\n\n");

                startActivity(Intent.createChooser(intent, "Send Email"));
            }

        }
        if (id == R.id.action_call){

            if(phoneNumber != null){
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+phoneNumber));
                startActivity(callIntent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mapView != null)
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mapView != null)
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mapView != null)
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if(mapView != null)
        mapView.onLowMemory();
    }
}
