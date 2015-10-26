package com.example.ajla.peoplemanagement;

/**
 * Created by ajla.eltabari on 26/10/15.
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PersonList {
    private static List<PersonModel> personList;

    public PersonList() {
        personList = new ArrayList<>();
    }

    public void addPerson(String name, String surname) {
        personList.add(new PersonModel(name.toString(), surname.toString()));
    }

    public static PersonModel getPersonById(String id){
        for(int i = 0; i < personList.size(); i++) {
            if(personList.get(i).getPersonsId().equals(id)) {
                return personList.get(i);
            }
        }
        return null;
    }

    public static void updatePerson(String id, String name, String surname) {
        PersonModel u = getPersonById(id);
        u.setName(name);
        u.setSurname(surname);
    }

    public PersonModel getPerson(int index) {
        return personList.get(index);
    }

    public int getSize() {
        return personList.size();
    }

    public static void deletePerson(String id) {
        Iterator<PersonModel> userIterator = personList.iterator();
        while (userIterator.hasNext()) {
            if(userIterator.next().getPersonsId().equals(id)) {
                userIterator.remove();
                return;
            }
        }
    }
}
