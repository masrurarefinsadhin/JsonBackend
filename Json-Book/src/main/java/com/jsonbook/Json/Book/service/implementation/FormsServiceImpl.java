package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.RequestBodyType;
import com.jsonbook.Json.Book.entity.Forms;
import com.jsonbook.Json.Book.repository.FormsRepository;
import com.jsonbook.Json.Book.service.FormsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormsServiceImpl implements FormsService {
    private final FormsRepository formsRepository;

    public FormsServiceImpl(FormsRepository formsRepository) {
        this.formsRepository = formsRepository;
    }

    @Override
    public List<Forms> findAllForms() {
        return formsRepository.findAll();

    }

    @Override
    public Forms addForms(Forms forms) {
        return formsRepository.save(forms);
    }

    @Override
    public void deleteForms(long id) {
        formsRepository.deleteById(id);
    }

    @Override
    public Forms updateForms(Forms forms) {
        return formsRepository.save(forms);
    }

    @Override
    public List<Forms> findFormsByRequestId(long id, RequestBodyType requestBodyType) {
        return formsRepository.findFormsByRequestId(id,requestBodyType.toString());
    }
}
