package io.cuesoft.apparule.model;

public class Designer {

    public String businessname;
    public String email;
    public String address;
    public String country;

    public Designer(){
        //Default constructor required for calls to DataSnapShot.getValue(Use.class)
    }

    public Designer(String businessname, String email, String address
                    ,String country){

        this.businessname = businessname;
        this.email = email;
        this.address = address;
        this.country = country;
    }

}
