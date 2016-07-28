package cz.uhk.cityuhelper;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import cz.uhk.cityuhelper.model.Author;
import cz.uhk.cityuhelper.model.Delivery;
import cz.uhk.cityuhelper.model.Item;

/**
 * Created by alab on 7/27/2016.
 */
public class FakeDataBuilder {

    public static ArrayList<Item> getListOfFakeDelivers(){
        ArrayList<Item> deliveries = new ArrayList<>();

        //FAKE DATA
        Author peter = new Author("Petr","Weissar","weisspe1@uhk.cz","Czech developer from Hradec Králové","725791445");
        Author vladimir = new Author("Vladimir","Maliniak", "malinvl1@uhk.cz","Czech UHK student from Prague","7879445");
        Author kai = new Author("Kai","TODO","TODO@uhk.cz","Chinesse","6518789");
        Author jarett = new Author("Jarett","TODO","TODO@uhk.cz","Chinesse","6578132");

        deliveries.add(new Delivery("Sushi takeout", peter, new LatLng(22.325244, 114.168264)));
        deliveries.add(new Delivery("Make reservation table 11", peter, new LatLng(22.325412, 114.169088)));

        deliveries.add(new Delivery("Amazon book takeof", vladimir, new LatLng(22.336306, 114.169087)));
        deliveries.add(new Delivery("Chinesse pot ordering", vladimir, new LatLng(22.328347, 114.165139)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));

        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));

        //deliveries.add(new Delivery("Make reservation table 11", peter, new LatLng(22.325412, 114.169088)));
        //deliveries.add(new Delivery("Make reservation table 11", peter, new LatLng(22.325412, 114.169088)));

        return deliveries;
    }
}
