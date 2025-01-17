package com.example.cop_rut.model;

import com.example.cop_rut.model.base_classes.BaseEntity;
import com.example.cop_rut.model.base_classes.BaseEntityCM;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "clients")
public class Client extends BaseEntityCM {
    private String name;
    private String phoneNumber;
    private String email;
    private List<String> orders = new ArrayList<>();
    private boolean isDelete;

    public Client() {
    }

    public Client(String name, String phoneNumber, String email, List<String> orders, boolean isDelete) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.orders = orders;
        this.isDelete = isDelete;
    }

    public Client(LocalDateTime createDate, LocalDateTime updateDate, String name, String phoneNumber, String email, List<String> orders, boolean isDelete) {
        super(createDate, updateDate);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.orders = orders;
        this.isDelete = isDelete;
    }

    public void addOrder(String order){
        this.orders.add(order);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", orders=" + orders +
                ", isDelete=" + isDelete +
                '}';
    }
}
