package cz.uhk.cityuhelper.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by petrw on 25.07.2016.
 */
public class Food extends Item {

    public Food(String title, Author author, LatLng position){
        super(title,author,position);
        this.type = Type.FOOD;
    }
}
