package com.example.cop_rut_contracts.dtos.base;


import com.example.cop_rut_contracts.enam.order.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    private String uuid;
    private CleaningType cleaningType;
    private ExecutionStatus executionStatus;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime execution;//дата исполнения
    private String orderNumber;
    private DistanceFromCenter distanceFromCenter;
    private List<AdditionalService> additionalServices;
    private String customerComment;
    private String executorComment;
    private RedFlag redFlag;
    private BigDecimal cost;
    private boolean archived;
    private List<SpaceDto> space;
    private String client;
    private String brigade;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    public OrderDto() {
    }

    public void addRoom(SpaceDto room){
        this.space.add(room);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public LocalDateTime getExecution() {
        return execution;
    }

    public void setExecution(LocalDateTime execution) {
        this.execution = execution;
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

    public List<SpaceDto> getSpace() {
        return space;
    }

    public void setSpace(List<SpaceDto> space) {
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
        return "OrderDto{" +
                "uuid='" + uuid + '\'' +
                ", cleaningType=" + cleaningType +
                ", executionStatus=" + executionStatus +
                ", execution=" + execution +
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
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
