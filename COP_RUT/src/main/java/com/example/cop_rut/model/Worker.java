package com.example.cop_rut.model;


import com.example.cop_rut.model.base_classes.BaseEntity;
import com.example.cop_rut.model.base_classes.BaseEntityCM;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "worker")
public class Worker extends BaseEntityCM {
    private String name;
    private String phoneNumber;
    private boolean isOnShift;
    private String brigade;
    private boolean dismissed;

    public Worker() {
    }
    public Worker(String name, String phoneNumber, boolean isOnShift, String brigade) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isOnShift = isOnShift;
        this.brigade = brigade;
    }

    public Worker(LocalDateTime createDate, LocalDateTime updateDate, String name, String phoneNumber, boolean isOnShift, String brigade, boolean dismissed) {
        super(createDate, updateDate);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isOnShift = isOnShift;
        this.brigade = brigade;
        this.dismissed = dismissed;
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

    public boolean isOnShift() {
        return isOnShift;
    }

    public void setOnShift(boolean onShift) {
        isOnShift = onShift;
    }

    public String getBrigade() {
        return brigade;
    }

    public void setBrigade(String brigade) {
        this.brigade = brigade;
    }

    public boolean isDismissed() {
        return dismissed;
    }

    public void setDismissed(boolean dismissed) {
        this.dismissed = dismissed;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isOnShift=" + isOnShift +
                ", brigade='" + brigade + '\'' +
                ", dismissed=" + dismissed +
                '}';
    }
}
