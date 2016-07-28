package cz.uhk.cityuhelper.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by petrw on 25.07.2016.
 */
public class Item implements IItem, Serializable {

    //ID is here for future reasons
    public final String FAKE_ID = "fakeIdXY";

    protected String id;
    protected String title;
    protected Author author;
    protected Type type;
    protected LatLng position;


    public Item(String title, Author author, LatLng position){
        this.id = FAKE_ID; //TODO - sometimes it should change
        this.title = title;
        this.author = author;
        this.position = position;
    }

    public enum Type{

        DELIVER("Delivery"),
        PRINT("Print"),
        FOOD("Food");

        private String toString;

        Type(String toString){
            this.toString = toString;
        }

        @Override
        public String toString(){
            return toString;
        }
    }

    //GETTERS & SETTERS
    public String getId(){
        return FAKE_ID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }
}
