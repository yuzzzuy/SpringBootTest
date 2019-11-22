package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * FileName: Teacher
 * Author:   10347
 * Date:     2019/11/8 10:10
 * Description:
 */
@ApiModel( description = "教师")
@Entity
@Table(name = "teacher")
@DynamicInsert
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Teacher implements Serializable {

    private static final long serialVersionUID = -936172627757232265L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键id")
    long id;
    @Column(name = "name")
    @ApiModelProperty(value = "名字")
    String name;
    @Transient
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
            .append(id);
        sb.append(",\"name\":\"")
            .append(name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
