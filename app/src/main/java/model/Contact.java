package model;

import java.util.List;

public class Contact {

    // Attributes de la classe Contact
    private String id;
    private String name;
    private String lname;

    private List<Phone> phone;
    private List<MailAddress> email;
    private List<String> address;



    // Constructeur de la classe Contact
    public Contact(String id, String name, String lname, List<Phone> phone, List<MailAddress> email, List<String> address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.lname = lname;
    }

    // Getters et Setters de la classe Contact
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

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public List<MailAddress> getEmail() {
        return email;
    }

    public void setEmail(List<MailAddress> email) {
        this.email = email;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public String getLastName() {
        return lname;
    }

    public void setLastName(String lname) {
        this.lname = lname;
    }

}
