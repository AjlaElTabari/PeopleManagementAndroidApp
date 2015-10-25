package com.example.ajla.peoplemanagement;

import android.content.Intent;
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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final Integer SORT_BY_NAME = 0;
    private static final Integer SORT_BY_SURNAME = 1;
    public static final String EDIT = "edit";
    private static List<PersonModel> persons = new ArrayList<>();

    private EditText fieldName;
    private EditText fieldSurname;

    private Button addButton;
    private Button deleteButton;

    private RecyclerView recyclerView;
    private PersonAdapter adapter;

    private RadioButton rbtnSortByName;
    private RadioButton rbtnSortBySurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldName = (EditText) findViewById(R.id.name);
                fieldSurname = (EditText) findViewById(R.id.surname);
        addButton = (Button) findViewById(R.id.btnAdd);
        recyclerView = (RecyclerView) findViewById(R.id.persons);

        rbtnSortByName = (RadioButton) findViewById(R.id.rbtnSortByName);
        rbtnSortByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                compareAndSort(SORT_BY_NAME);

                rbtnSortBySurname.setChecked(false);
                updateList();
            }
        });

        rbtnSortBySurname = (RadioButton) findViewById(R.id.rbtnSortBySurname);
        rbtnSortBySurname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                compareAndSort(SORT_BY_SURNAME);

                rbtnSortByName.setChecked(false);
                updateList();
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable name = fieldName.getText();
                Editable surname = fieldSurname.getText();

                PersonModel person = new PersonModel(name, surname);

                persons.add(0, person);

                adapter.notifyDataSetChanged();

                fieldName.setText("");
                fieldSurname.setText("");


                fieldName.requestFocus();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PersonAdapter();
        recyclerView.setAdapter(adapter);
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
            nameView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Edit.class);
                    Bundle personToEdit = new Bundle();
                    personToEdit.putSerializable(EDIT, person);
                    i.putExtras(personToEdit);
                    startActivity(i);
                }
            });

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

    private void compareAndSort(final Integer sortBy) {
        Collections.sort(persons, new Comparator<PersonModel>() {
            @Override
            public int compare(PersonModel person1, PersonModel person2) {
                if (sortBy == SORT_BY_NAME) {
                    return person1.getPersonsName().compareToIgnoreCase(person2.getPersonsName());
                } else {
                    return person1.getPersonsSurname().compareToIgnoreCase(person2.getPersonsSurname());
                }
            }
        });
    }
}