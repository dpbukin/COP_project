package com.example.cop_rut.dtos.base;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class BrigadeDto {
    private String uuid;
    private String phoneNumber;
    private List<String> workersId;
    private Boolean freeness;
    private String order;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime createDate;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime updateDate;

    public BrigadeDto() {
    }

    public void addWorker(String worker){
        this.workersId.add(worker);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getWorkersId() {
        return workersId;
    }

    public void setWorkersId(List<String> workersId) {
        this.workersId = workersId;
    }

    public Boolean getFreeness() {
        return freeness;
    }

    public void setFreeness(Boolean freeness) {
        this.freeness = freeness;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

//    public LocalDateTime getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(LocalDateTime createDate) {
//        this.createDate = createDate;
//    }
//
//    public LocalDateTime getUpdateDate() {
//        return updateDate;
//    }
//
//    public void setUpdateDate(LocalDateTime updateDate) {
//        this.updateDate = updateDate;
//    }

    @Override
    public String toString() {
        return "BrigadeDto{" +
                "uuid='" + uuid + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", workersId=" + workersId +
                ", freeness=" + freeness +
                ", order='" + order + '\'' +
//                ", createDate=" + createDate +
//                ", updateDate=" + updateDate +
                '}';
    }
}
