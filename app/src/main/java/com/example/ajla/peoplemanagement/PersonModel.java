package com.example.ajla.peoplemanagement;

import android.text.Editable;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;
import java.util.UUID;
/**
 * Created by ajla on 10/24/15.
 */
public class PersonModel {

    private UUID id;
    private String name;
    private String surname;
    private String timestamp;

    public PersonModel(Editable name, Editable surname) {
        this.id = UUID.randomUUID();
        this.name = name.toString();
        this.surname = surname.toString();
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
}
