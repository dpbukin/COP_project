//package com.example.cop_rut.model;
//
//import com.example.cop_rut.model.base_classes.BaseEntity;
//import com.example.cop_rut.model.base_classes.BaseEntityCM;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Document(collection = "brigade")
//public class Brigade extends BaseEntityCM {
//    private String phoneNumber;
//    private List<String> workersId;
//    private Boolean freeness;
//    private String order;
//
//    public Brigade() {
//    }
//
//    public Brigade(String phoneNumber, List<String> workersId, Boolean freeness, String order) {
//        this.phoneNumber = phoneNumber;
//        this.workersId = workersId;
//        this.freeness = freeness;
//        this.order = order;
//    }
//
//    public Brigade(LocalDateTime createDate, LocalDateTime updateDate, String phoneNumber, List<String> workersId, Boolean freeness, String order) {
//        super(createDate, updateDate);
//        this.phoneNumber = phoneNumber;
//        this.workersId = workersId;
//        this.freeness = freeness;
//        this.order = order;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public List<String> getWorkersId() {
//        return workersId;
//    }
//
//    public void setWorkersId(List<String> workersId) {
//        this.workersId = workersId;
//    }
//
//    public Boolean getFreeness() {
//        return freeness;
//    }
//
//    public void setFreeness(Boolean freeness) {
//        this.freeness = freeness;
//    }
//
//    public String getOrder() {
//        return order;
//    }
//
//    public void setOrder(String order) {
//        this.order = order;
//    }
//
//    @Override
//    public String toString() {
//        return "Brigade{" +
//                "phoneNumber='" + phoneNumber + '\'' +
//                ", workersId=" + workersId +
//                ", freeness=" + freeness +
//                ", order='" + order + '\'' +
//                '}';
//    }
//}
