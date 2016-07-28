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
        Author jarrett = new Author("Jarett","TODO","TODO@uhk.cz","Chinesse","6578132");

        deliveries.add(new Delivery("Sushi takeout", peter, new LatLng(22.325244, 114.168264)));
        deliveries.add(new Delivery("Make reservation table 11", peter, new LatLng(22.325412, 114.169088)));

        deliveries.add(new Delivery("Amazon book takeof", vladimir, new LatLng(22.336306, 114.169087)));
        deliveries.add(new Delivery("Chinesse pot ordering", vladimir, new LatLng(22.328347, 114.165139)));
        deliveries.add(new Delivery("Museum ticket buy", vladimir, new LatLng(22.333825, 114.163476)));

        deliveries.add(new Delivery("KFC food", kai, new LatLng(22.3397947, 114.1703036)));
        deliveries.add(new Delivery("Package - glass inside", jarrett, new LatLng(22.3359542, 114.1736939)));
        deliveries.add(new Delivery("Printing 50 pages", peter, new LatLng(22.3385742, 114.1705825)));
        deliveries.add(new Delivery("Letter from Europe", vladimir, new LatLng(22.3378397, 114.1779747)));
        deliveries.add(new Delivery("Sky100 ticket", peter, new LatLng(22.3356764, 114.1667308)));
        deliveries.add(new Delivery("Where to find class 47", vladimir, new LatLng(22.3259803, 114.1669883)));
        deliveries.add(new Delivery("Sushi delivery", kai, new LatLng(22.3400825, 114.1584589)));
        deliveries.add(new Delivery("Beverage for party", kai, new LatLng(22.3379836, 114.1741875)));
        deliveries.add(new Delivery("Flash disk delivery", peter, new LatLng(22.3372294, 114.1754106)));
        deliveries.add(new Delivery("Printing attached document", jarrett, new LatLng(22.3353439, 114.1702178)));
        deliveries.add(new Delivery("Museum ticket buy", jarrett, new LatLng(22.3370508, 114.1657975)));
        deliveries.add(new Delivery("McDonald´s food delivery", kai, new LatLng(22.3300444, 114.1735650)));
        deliveries.add(new Delivery("KFC food delivery", vladimir, new LatLng(22.3454461, 114.1705181)));
        deliveries.add(new Delivery("Printing attached paperwork", peter, new LatLng(22.3387725, 114.1738925)));

        //deliveries.add(new Delivery("Make reservation table 11", peter, new LatLng(22.325412, 114.169088)));
        //deliveries.add(new Delivery("Make reservation table 11", peter, new LatLng(22.325412, 114.169088)));

        return deliveries;
    }
}
