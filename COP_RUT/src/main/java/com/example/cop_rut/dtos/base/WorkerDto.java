package com.example.cop_rut.dtos.base;

import com.example.cop_rut.model.base_classes.BaseEntityCM;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class WorkerDto extends BaseEntityCM {
    private String uuid;
    private String name;
    private String phoneNumber;
    private boolean isOnShift; //В бригаде
    private String brigade;
    private boolean dismissed; //Уволен
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;
    public WorkerDto() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "WorkerDto{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isOnShift=" + isOnShift +
                ", brigade='" + brigade + '\'' +
                ", dismissed=" + dismissed +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
