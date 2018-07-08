/*
Name: Jeremy Moore and Alec Maly
Net ID: jmoore28 and amaly1
COSC 311 Course Project
*/

package pro.jmoore.stablemarriage;

public class Driver {
    private Person[] men;
    private Person[] women;
    private String message;
    private String lastMessage;
    private int index;

    public Driver(int size) {
        men = new Person[size];
        women = new Person[size];
        populate(size);
        message = "";
        index = 0;
    }
    
    // REQUIRED FOR JSTL
    public Person[] getMen() {
        return men;
    }

    // REQUIRED FOR JSTL
    public Person[] getWomen() {
        return women;
    }

    // REQUIRED FOR JSTL
    public String getMessage() {
        return message;
    }
    
    // REQUIRED FOR JSTL
    public String getLastMessage() {
        return lastMessage;
    }

    // ONE STEP AT A TIME
    public void step() {
        int marker = index;
        while (men[index].getPartner() != null) {
            index = (index + 1) % men.length;
            if (index == marker)
                return;
            if (index == 0)
                message += "<br>";
        }
        lastMessage = men[index].propose() + "<br>";
        message += lastMessage;
        index = (index + 1) % men.length;
        if (index == 0)
            message += "<br>";
    }

    // ONE ROUND OR DAY AT A TIME
    public void round() {
        do {
            if (men[index].getPartner() == null)
                message += men[index].propose() + "<br>";
        index = (index + 1) % men.length;
        } while (index != 0);
            message += "<br>";
    }
    
    // OPTIONAL METHOD: ONE STEP AT A TIME WITH WOMEN PROPOSING
    public void stepWomen() {
        int marker = index;
        while (women[index].getPartner() != null) {
            index = (index + 1) % women.length;
            if (index == marker)
                return;
            if (index == 0)
                message += "<br>";
        }
        message += women[index].propose() + "<br>";
        index = (index + 1) % women.length;
        if (index == 0)
            message += "<br>";
    }

    // OPTIONAL METHOD: ONE ROUND AT A TIME WITH WOMEN PROPOSING
    public void roundWomen() {
        do {
            if (women[index].getPartner() == null)
                message += women[index].propose() + "<br>";
        index = (index + 1) % women.length;
        } while (index != 0);
            message += "<br>";
    }

    private void populate(int size) {
        Person bob = new Person("Bob", size);
        Person tom = new Person("Tom", size);
        Person dave = new Person("Dave", size);
        Person john = new Person("John", size);
        Person joe = new Person("Joe", size);
        men[0] = bob;
        men[1] = tom;
        men[2] = dave;
        men[3] = john;
        men[4] = joe;

        Person mary = new Person("Mary", size);
        Person meg = new Person("Meg", size);
        Person sara = new Person("Sara", size);
        Person barb = new Person("Barb", size);
        Person gina = new Person("Gina", size);
        women[0] = mary;
        women[1] = meg;
        women[2] = sara;
        women[3] = barb;
        women[4] = gina;

        bob.setPreferences(mary, meg, sara, barb, gina);
        tom.setPreferences(mary, sara, meg, gina, barb);
        dave.setPreferences(sara, gina, mary, meg, barb);
        john.setPreferences(gina, sara, barb, meg, mary);
        joe.setPreferences(sara, gina, mary, meg, barb);

        mary.setPreferences(dave, john, joe, bob, tom);
        meg.setPreferences(john, tom, bob, joe, dave);
        sara.setPreferences(john, joe, tom, bob, dave);
        barb.setPreferences(tom, dave, joe, bob, john);
        gina.setPreferences(bob, tom, dave, john, joe);
    }

}
