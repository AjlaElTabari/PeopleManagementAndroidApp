package com.example.ajla.peoplemanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static List<PersonModel> persons = new ArrayList<>();

    private EditText fieldName;
    private EditText fieldSurname;

    private Button addButton;
    private Button deleteButton;

    private RecyclerView recyclerView;
    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldName = (EditText) findViewById(R.id.name);
        fieldSurname = (EditText) findViewById(R.id.surname);
        addButton = (Button) findViewById(R.id.btnAdd);
        recyclerView = (RecyclerView) findViewById(R.id.persons);

        addButton.setOnClickListener(new Click());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PersonAdapter();
        recyclerView.setAdapter(adapter);
    }

    private class Click implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Editable name = fieldName.getText();
            Editable surname = fieldSurname.getText();

            PersonModel person = new PersonModel(name, surname);

            persons.add(0, person);

            adapter.notifyDataSetChanged();

            fieldName.setText("");
            fieldSurname.setText("");
        }
    }

    private class PersonHolder extends RecyclerView.ViewHolder {

        private TextView idView;
        private TextView nameView;
        private TextView surnameView;
        private TextView timestampView;
        private Button btnDeletePerson;

        private PersonModel person;

        public PersonHolder(View itemView) {

            super(itemView);

            idView = (TextView) itemView.findViewById(R.id.personId);
            nameView = (TextView) itemView.findViewById(R.id.personName);
            surnameView = (TextView) itemView.findViewById(R.id.personSurname);
            timestampView = (TextView) itemView.findViewById(R.id.personTimestamp);
            btnDeletePerson = (Button) itemView.findViewById(R.id.btnDeletePerson);

            btnDeletePerson.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    for (int i = 0; i < persons.size(); i++) {
                        String id = idView.getText().toString();
                        if (persons.get(i).getPersonsId().equals(id)) {
                            persons.remove(i);
                        }
                    }
                    updateList();
                }
            });
        }
    }

    private class PersonAdapter extends RecyclerView.Adapter<PersonHolder> {

        @Override
        public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View view = layoutInflater.inflate(R.layout.person, parent, false);

            return new PersonHolder(view);
        }

        @Override
        public void onBindViewHolder(PersonHolder holder, int position) {

            PersonModel person = persons.get(position);

            holder.idView.setText(person.getPersonsId());
            holder.nameView.setText(person.getPersonsName());
            holder.surnameView.setText(person.getPersonsSurname());
            holder.timestampView.setText(person.getPersonsTimestamp());

            holder.person = person;
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }
    }


    public void updateList() {
        adapter = new PersonAdapter();
        recyclerView.setAdapter(adapter);
    }
}