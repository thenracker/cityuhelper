package cz.uhk.cityuhelper;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by petrw on 27.07.2016.
 */
public class GeopositionHelper {

    private final static String MAP_API_KEY = "AIzaSyCS3_oLzNTmJiS_DhfpKFb2UwvY0AZDfFM";

    //CLASS WHICH HAVE FUNCTIONS TO FIND ADDRESS BY LATLNG
    public void findAdressByLatLng(LatLng latLng) {

        try {
            String urlString = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+latLng.latitude+","+latLng.longitude+"&key="+MAP_API_KEY;
            JSONTokener tokener = new JSONTokener(urlString);
            JSONObject root = new JSONObject(tokener);
            root.get("Address");

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
