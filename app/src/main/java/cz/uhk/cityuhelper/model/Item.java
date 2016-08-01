package cz.uhk.cityuhelper.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by petrw on 25.07.2016.
 */
public class Item implements IItem, Serializable {

    protected String id;
    protected String title;
    protected String description;
    protected Author author;
    protected Type type;
    protected MyLatLng position;


    public Item(Type type, String title, String description, Author author, MyLatLng position){
        this.id = UUID.randomUUID().toString(); //TODO add testing if it exists??
        this.title = title;
        this.description = description;
        this.author = author;
        this.position = position;
        this.type = type;
    }

    public enum Type{

        DELIVER("Deliver"),
        PRINT("Print"),
        FOOD("Food"),
        NEED("Need"),
        QUESTION("Question");

        private String toString;

        Type(String toString){
            this.toString = toString;
        }

        @Override
        public String toString(){
            return toString;
        }

        public static String[] getStringValues(){
            String[] strings = new String[values().length];
            for(int i = 0; i < values().length; i++){
                strings[i] = values()[i].toString();
            }
            return strings;
        }
    }

    //GETTERS & SETTERS
    public String getId(){
        return this.id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public MyLatLng getPosition() {
        return position;
    }

    public void setPosition(MyLatLng position) {
        this.position = position;
    }
}
