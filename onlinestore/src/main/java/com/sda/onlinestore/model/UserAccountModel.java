package com.sda.onlinestore.model;

import javax.persistence.*;

@Entity
@Table
public class UserAccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String login;
    private String password;
    private String city;
    @OneToOne
    private UserAdressModel userAdress;
    @OneToOne
    private UserAdressModel deliveryAdress;
    private String logotype;
    @OneToOne
    private Role roletype;

    public UserAdressModel getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(UserAdressModel userAdress) {
        this.userAdress = userAdress;
    }

    public UserAdressModel getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(UserAdressModel deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

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


    public String getLogotype() {
        return logotype;
    }

    public void setLogotype(String logotype) {
        this.logotype = logotype;
    }

    public Role getRoletype() {
        return roletype;
    }

    public void setRoletype(Role roletype) {
        this.roletype = roletype;
    }
}
