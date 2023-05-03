package model;

public class MailAddress {

    private String mail;
    private String type;

    public MailAddress(String mail, String type) {
        this.mail = mail;
        this.type = type;

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
