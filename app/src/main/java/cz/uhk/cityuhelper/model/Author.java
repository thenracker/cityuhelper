package cz.uhk.cityuhelper.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by petrw on 25.07.2016.
 */
public class Author implements Serializable {

    private String id;
    private String name;
    @NonNull
    private String email;
    private String telephone;

    public Author(String name, String email, String telephone){
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone(){return telephone;}

    public void setTelephone(String telephone) {this.telephone =telephone;}

}
