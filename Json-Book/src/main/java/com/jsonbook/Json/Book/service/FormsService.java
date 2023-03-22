package com.jsonbook.Json.Book.service;

import com.jsonbook.Json.Book.RequestBodyType;
import com.jsonbook.Json.Book.entity.Forms;
import com.jsonbook.Json.Book.entity.Groups;
import com.jsonbook.Json.Book.entity.Requests;

import java.text.Normalizer;
import java.util.List;

public interface FormsService {
    List<Forms> findAllForms();
    Forms addForms(Forms forms);
    void deleteForms(long id);
    Forms updateForms(Forms forms);
    List<Forms> findFormsByRequestId(long id, RequestBodyType requestBodyType);
}
