package com.example.ajla.peoplemanagement;

import android.text.Editable;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
/**
 * Created by ajla on 10/24/15.
 */
public class PersonModel implements Serializable {

    private String id;
    private String name;
    private String surname;
    private String timestamp;

    public PersonModel(Editable name, Editable surname) {
        this.id = UUID.randomUUID().toString();
        this.name = name.toString();
        this.surname = surname.toString();
        this.timestamp = new Date().toString();
    }

    public PersonModel(String name, String surname) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.timestamp = new Date().toString();
    }

    public String getPersonsId() {
        return id.toString();
    }

    public String getPersonsName() {
        return name;
    }

    public String getPersonsSurname() {
        return surname;
    }

    public String getPersonsTimestamp() {
        return timestamp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return name + " " + surname + " id" + id + " date" + timestamp;
    }
}
