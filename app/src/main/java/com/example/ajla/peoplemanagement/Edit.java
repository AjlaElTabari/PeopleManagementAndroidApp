package com.example.ajla.peoplemanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ajla on 10/25/15.
 */
public class Edit extends AppCompatActivity {

    private PersonModel person;

    private String id;
    private EditText editName;
    private EditText editSurname;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);

        person = (PersonModel) getIntent().getExtras().getSerializable(MainActivity.EDIT);

        id = person.getPersonsId();

        editName = (EditText) findViewById(R.id.editName);
        editName.setText(person.getPersonsName());

        editSurname = (EditText) findViewById(R.id.editSurname);
        editSurname.setText(person.getPersonsSurname());

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PersonList.updatePerson(id, editName.getText().toString(), editSurname.getText().toString());

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}