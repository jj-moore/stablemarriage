/*
Name: Jeremy Moore and Alec Maly
Net ID: jmoore28 and amaly1
COSC 311 Course Project
*/

package pro.jmoore.stablemarriage;

public class Person {

    private String name;
    private Person partner;
    private Person[] preferences;
    private String ex; // ONLY NEEDED SO I CAN PRINT WHO WAS DUMPED.
    private int pointer;

    Person(String name, int size) {
        this.name = name;
        pointer = 0;
        preferences = new Person[5];
    }

    // REQUIRED FOR JSTL
    public Person getPartner() {
        return partner;
    }

    // REQUIRED FOR JSTL
    public String getName() {
        return name;
    }

    // REQUIRED FOR JSTL
    public Person[] getPreferences() {
        return preferences;
    }

    // INITIAL SETUP FOR PREFERENCES ARRAY
    public void setPreferences(Person... person) {
        int count = 0;
        for (Person p : person) {
            preferences[count] = p;
            count++;
        }
    }

    // ONE PERSON PROPOSING TO ANOTHER
    // ACTUAL WORK IS DONE BY METHOD: determinePartner
    // THIS JUST PROCESSES THE RESULT
    // IF AFTER determinePartner THEY DO NOT HAVE A PARTNER THAN THEY WERE REJECTED.
    String propose() {
        Person person = preferences[pointer];
        partner = determinePartner(person);
        String result;
        if (partner == null) {
            result = person.name + " rejects. ";
        } else if (ex == null || ex.isEmpty()) {
            result = person.name + " accepts. ";
        } else {
            result = person.name + " dumps " + ex + ", gets engaged to " + name + ".";
        }
        pointer++;
        ex = "";
        return name + " proposes to " + person.name + ". " + result;
    }

    // IF ACCEPTOR HAS NO PARTNER, ACCEPT.
    // IF ACCEPTOR HAS A PARTNER, STEP THROUGH ACCEPTOR PREFERENCES
    // IF YOU FIND CURRENT PARTNER FIRST, PROPOSER IS REJECTED
    // IF YOU FIND PROPOSER FIRST, CURRENT PARTNER IS DUMPED
    Person determinePartner(Person acceptor) {
        if (acceptor.partner == null) {
            acceptor.partner = this;
            return acceptor;
        }

        for (Person p : acceptor.preferences) {
            if (p.equals(acceptor.partner)) {
                return null;
            }
            if (p.equals(this)) {
                ex = acceptor.partner.name;
                acceptor.partner.partner = null;
                acceptor.partner = this;
                return acceptor;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other != null && other.getClass() == this.getClass()) {
            Person otherPerson = (Person) other;
            return name.equals(otherPerson.name);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return name;
    }

}
