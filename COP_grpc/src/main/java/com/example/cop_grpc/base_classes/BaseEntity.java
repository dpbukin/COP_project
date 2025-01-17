package com.example.cop_grpc.base_classes;

import org.springframework.data.annotation.Id;

public abstract class BaseEntity {
    @Id
    private String id;
    private String uuid;
    public BaseEntity() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id='" + id + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
