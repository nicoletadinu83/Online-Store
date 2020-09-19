package com.sda.onlinestore.model;

import javax.persistence.*;

@Entity
@Table
public class UserAccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "city")
    private String city;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userAccountModel")//mapat dupa membrul clasei UserAdressModel
    @Column(name = "userAdress")
    private UserAdressModel userAdress;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userAccountModel")
    @Column(name = "deliveryAdress")
    private UserAdressModel deliveryAdress;

    @Column(name = "logotype")
    private String logotype;

    @OneToOne(cascade =CascadeType.ALL)
    @Column(name = "roletype")
    private RoleModel roletype;


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

    public RoleModel getRoletype() {
        return roletype;
    }

    public void setRoletype(RoleModel roletype) {
        this.roletype = roletype;
    }
}
