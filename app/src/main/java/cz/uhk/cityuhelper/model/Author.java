package cz.uhk.cityuhelper.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by petrw on 25.07.2016.
 */
public class Author implements Serializable {

    private String id;
    private String name;
    private String surname;
    @NonNull
    private String email;
    private String description;
    private String telephone;

    public Author(String name, String surname, String email, String description, String telephone){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.description = description;
        this.telephone = telephone;
    }

    //GETTERS & SETTERS
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
