package cz.uhk.cityuhelper;

import java.util.ArrayList;

import cz.uhk.cityuhelper.model.Delivery;

/**
 * Created by alab on 7/27/2016.
 */
public class FakeDataBuilder {

    public ArrayList<Delivery> getListOfFakeDelivers(){
        ArrayList<Delivery> deliveries = new ArrayList<>();

        deliveries.add(new Delivery("id","name"));

        return deliveries;
    }
}
