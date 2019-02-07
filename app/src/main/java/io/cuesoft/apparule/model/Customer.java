package io.cuesoft.apparule.model;

public class Customer {

    public String fullname;
    public String email;
    public int profilepic;


    public Customer(){

    }

    public Customer(String fullname, String email ){
        this.fullname = fullname;
        this.email = email;
    }

}
