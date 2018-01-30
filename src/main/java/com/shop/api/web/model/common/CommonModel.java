package com.shop.api.web.model.common;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public class CommonModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "created_by", length = 64)
    protected String createdBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "creation_time", length = 100)
    protected Date creationTime;

    @Column(name = "creation_method", length = 100)
    protected String creationMethod;

    @Column(name = "updated_by", length = 64)
    protected String updatedBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time", length = 100)
    protected Date updateTime;

    @Column(name = "update_method", length = 100)
    protected String updateMethod;

    @Column(name = "delete_flag", length = 1)
    protected Integer deleteFlag = 0;
}
