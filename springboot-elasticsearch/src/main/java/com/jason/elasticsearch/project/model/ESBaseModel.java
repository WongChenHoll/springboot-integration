package com.jason.elasticsearch.project.model;

import org.springframework.data.annotation.Id;

/**
 * @author WongChenHoll
 * @date 2022-5-12 星期四 15:38
 **/
public class ESBaseModel {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
