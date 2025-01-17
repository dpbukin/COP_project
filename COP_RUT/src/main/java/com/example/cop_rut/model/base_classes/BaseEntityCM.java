package com.example.cop_rut.model.base_classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public abstract class BaseEntityCM extends BaseEntity {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    public BaseEntityCM() {
    }

    public BaseEntityCM(LocalDateTime createDate, LocalDateTime updateDate) {
        this.createDate = createDate;
        this.updateDate = updateDate;
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
        return "BaseEntityCM{" +
                "createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
