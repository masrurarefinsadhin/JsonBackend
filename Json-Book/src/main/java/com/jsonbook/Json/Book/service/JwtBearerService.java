package com.jsonbook.Json.Book.service;

import com.jsonbook.Json.Book.entity.JwtBearer;
import com.jsonbook.Json.Book.entity.Requests;

public interface JwtBearerService {
    // find JwtBearer authorization
    JwtBearer findJwtBearer(Requests requests);
    // add JwtBearer authorization
    JwtBearer addJwtBearer(JwtBearer jwtBearer);

    // delete JwtBearer authorization
    void deleteJwtBearer(long id);

    //update JwtBearer authorization
    JwtBearer updateJwrBearer(JwtBearer jwtBearer);


}
