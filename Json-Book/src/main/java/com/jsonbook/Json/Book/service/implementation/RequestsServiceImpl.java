package com.jsonbook.Json.Book.service.implementation;

import com.jsonbook.Json.Book.entity.Forms;
import com.jsonbook.Json.Book.entity.RequestFormDto;
import com.jsonbook.Json.Book.entity.Requests;
import com.jsonbook.Json.Book.repository.FormsRepository;
import com.jsonbook.Json.Book.repository.RequestsRepository;
import com.jsonbook.Json.Book.service.RequestsService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestsServiceImpl implements RequestsService {
    private final RequestsRepository requestsRepository;
    private final FormsRepository formsRepository;

    public RequestsServiceImpl(RequestsRepository requestsRepository, FormsRepository formsRepository) {
        this.requestsRepository = requestsRepository;
        this.formsRepository = formsRepository;
    }

    @Override
    public List<Requests> findAllRequests() {
        return requestsRepository.findAll();
    }

    @Override
    public Requests saveRequests(Requests requests) {
        return requestsRepository.save(requests);
    }

    @Override
    public Requests updateRequests(Requests requests) {
        return requestsRepository.save(requests);
    }

    @Override
    public void deleteRequests(long id) {
        requestsRepository.deleteById(id);
    }

    @Override
    public Requests findRequests(long id) {
        return requestsRepository.findById(id).get();
    }

    @Override
    public List<Requests> findRequestsById(long id) {
        return requestsRepository.findRequestById(id);
    }
    @Override
    public RequestFormDto saveRequestsForms(RequestFormDto requestFormDto){
        String forms=requestFormDto.getForms();
        Requests requests= requestFormDto.getRequests();
        requests=requestsRepository.save(requests);
        if (forms!=null && !forms.equals("{\"\":\"\"}")){
            JSONObject jsonObjectParam = new JSONObject(forms);
            for(String key: jsonObjectParam.keySet()){
                System.out.println(key);
                System.out.println((String) jsonObjectParam.get(key));
                Forms f= new Forms(null, key,(String) jsonObjectParam.get(key),requests.getRequestBodyType(),requests);
                formsRepository.save(f);
                System.out.println(f.toString());
            }
        }
        return  new RequestFormDto(requestFormDto.getRequests(),requestFormDto.getForms());
    }
}
