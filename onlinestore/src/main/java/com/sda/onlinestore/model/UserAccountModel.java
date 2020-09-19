package com.sda.onlinestore.model;

import javax.persistence.*;

@Entity
@Table
public class UserAccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private  String login;
    private String password;
    private String city;
    private UserAdressModel adress;
    private String logotype;
     private  Role usertype;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UserAdressModel getAdress() {
        return adress;
    }

    public void setAdress(UserAdressModel adress) {
        this.adress = adress;
    }

    public String getLogotype() {
        return logotype;
    }

    public void setLogotype(String logotype) {
        this.logotype = logotype;
    }

    public Role getUsertype() {
        return usertype;
    }

    public void setUsertype(Role usertype) {
        this.usertype = usertype;
    }

}
