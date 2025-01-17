package com.example.cop_rut.model;

import com.example.cop_rut.model.base_classes.BaseEntity;
import com.example.cop_rut.model.base_classes.BaseEntityCM;
import com.example.cop_rut.model.enam.order.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
public class Order extends BaseEntityCM {
    private CleaningType cleaningType;
    private ExecutionStatus executionStatus;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime executionDate;//дата исполнения
    private String orderNumber;
    private DistanceFromCenter distanceFromCenter;
    private List<AdditionalService> additionalServices;
    private String customerComment;
    private String executorComment;
    private RedFlag redFlag;
    private BigDecimal cost;
    private boolean archived;
    private List<Space> space;
    private String client;
    private String brigade;

    public Order() {
    }

    public Order(CleaningType cleaningType,
                 ExecutionStatus executionStatus,
                 LocalDateTime executionDate,
                 String orderNumber,
                 DistanceFromCenter distanceFromCenter,
                 List<AdditionalService> additionalServices,
                 String customerComment,
                 String executorComment,
                 RedFlag redFlag,
                 BigDecimal cost,
                 boolean archived,
                 List<Space> space,
                 String client,
                 String brigade) {
        this.cleaningType = cleaningType;
        this.executionStatus = executionStatus;
        this.executionDate = executionDate;
        this.orderNumber = orderNumber;
        this.distanceFromCenter = distanceFromCenter;
        this.additionalServices = additionalServices;
        this.customerComment = customerComment;
        this.executorComment = executorComment;
        this.redFlag = redFlag;
        this.cost = cost;
        this.archived = archived;
        this.space = space;
        this.client = client;
        this.brigade = brigade;
    }

    public Order(LocalDateTime createDate,
                 LocalDateTime updateDate,
                 CleaningType cleaningType,
                 ExecutionStatus executionStatus,
                 LocalDateTime executionDate,
                 String orderNumber,
                 DistanceFromCenter distanceFromCenter,
                 List<AdditionalService> additionalServices,
                 String customerComment,
                 String executorComment,
                 RedFlag redFlag,
                 BigDecimal cost,
                 boolean archived,
                 List<Space> space,
                 String client,
                 String brigade) {
        super(createDate, updateDate);
        this.cleaningType = cleaningType;
        this.executionStatus = executionStatus;
        this.executionDate = executionDate;
        this.orderNumber = orderNumber;
        this.distanceFromCenter = distanceFromCenter;
        this.additionalServices = additionalServices;
        this.customerComment = customerComment;
        this.executorComment = executorComment;
        this.redFlag = redFlag;
        this.cost = cost;
        this.archived = archived;
        this.space = space;
        this.client = client;
        this.brigade = brigade;
    }

    public CleaningType getCleaningType() {
        return cleaningType;
    }

    public void setCleaningType(CleaningType cleaningType) {
        this.cleaningType = cleaningType;
    }

    public ExecutionStatus getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime executionDate) {
        this.executionDate = executionDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public DistanceFromCenter getDistanceFromCenter() {
        return distanceFromCenter;
    }

    public void setDistanceFromCenter(DistanceFromCenter distanceFromCenter) {
        this.distanceFromCenter = distanceFromCenter;
    }

    public List<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getCustomerComment() {
        return customerComment;
    }
    public void setCustomerComment(String customerComment) {
        this.customerComment = customerComment;
    }
    public String getExecutorComment() {
        return executorComment;
    }
    public void setExecutorComment(String executorComment) {
        this.executorComment = executorComment;
    }
    public RedFlag getRedFlag() {
        return redFlag;
    }
    public void setRedFlag(RedFlag redFlag) {
        this.redFlag = redFlag;
    }
    public BigDecimal getCost() {
        return cost;
    }
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    public boolean isArchived() {
        return archived;
    }
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public List<Space> getSpace() {
        return space;
    }

    public void setSpace(List<Space> space) {
        this.space = space;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getBrigade() {
        return brigade;
    }

    public void setBrigade(String brigade) {
        this.brigade = brigade;
    }

    @Override
    public String toString() {
        return "Order{" +
                "cleaningType=" + cleaningType +
                ", executionStatus=" + executionStatus +
                ", executionDate=" + executionDate +
                ", orderNumber='" + orderNumber + '\'' +
                ", distanceFromCenter=" + distanceFromCenter +
                ", additionalServices=" + additionalServices +
                ", customerComment='" + customerComment + '\'' +
                ", executorComment='" + executorComment + '\'' +
                ", redFlag=" + redFlag +
                ", cost=" + cost +
                ", archived=" + archived +
                ", space=" + space +
                ", client='" + client + '\'' +
                ", brigade='" + brigade + '\'' +
                '}';
    }
}
