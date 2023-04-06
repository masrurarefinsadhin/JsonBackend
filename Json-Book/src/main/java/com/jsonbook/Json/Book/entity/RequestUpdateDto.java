package com.jsonbook.Json.Book.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RequestUpdateDto {
    private Requests requests;
    private List<Object> obj=new ArrayList<>();
    public void addObject(Object obj){
        this.obj.add(obj);
    }
}
