package cz.uhk.cityuhelper.model;

import java.io.Serializable;

/**
 * Created by petrw on 29.07.2016.
 */
public class MyLatLng implements Serializable{
    public double latitude, longitude;

    public MyLatLng(double la, double lo){
        this.latitude = la;
        this.longitude = lo;
    }
}
