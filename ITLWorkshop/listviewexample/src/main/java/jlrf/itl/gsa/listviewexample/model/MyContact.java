package jlrf.itl.gsa.listviewexample.model;

/**
 * Created by joseluisrf on 5/23/15.
 */
public class MyContact {

    public static final  String TABLE_NAME = "mycontacts";
    public static final  String NAME = "name";
    public static  final String MAIL = "mail";
    public static final String AGE= "age";
    public static final String ID = "mycontact_id";

    private String name;
    private String mail;
    private int age;
    private int mycontact_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMycontact_id() {
        return mycontact_id;
    }

    public void setMycontact_id(int mycontact_id) {
        this.mycontact_id = mycontact_id;
    }
}
