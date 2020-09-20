package com.sda.onlinestore.dto;

import com.sda.onlinestore.model.RoleModel;
import com.sda.onlinestore.model.UserAdressModel;

public class UserAccountDto {
    private Long id;
    private String login;
    private String password;
    private String city;
    private UserAdressModel userAdress;
    private UserAdressModel deliveryAdress;
    private String logotype;
    private RoleModel roletype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
