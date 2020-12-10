package com.vartdalen.mvc.java.spring.template.service;
import com.vartdalen.mvc.java.spring.template.model.ModelTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelTemplateService {

    //@Autowired
    //private OtherModelTemplateService otherModelTemplateService;

    @Value("${server.address}:${server.port}/modelTemplates") String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();

    public String helloWorld() {
        return "Hello, World";
    }

    public List<ModelTemplate> getModelTemplates() {
        return Arrays.stream(restTemplate.getForObject(BASE_URL, ModelTemplate[].class)).collect(Collectors.toList());
    }

    public ModelTemplate getModelTemplateById(long id) {
        return restTemplate.getForObject(BASE_URL+"/"+id, ModelTemplate.class);
    }

    public ModelTemplate postModelTemplate(ModelTemplate modelTemplate) {
        ResponseEntity<ModelTemplate> response;
        response = restTemplate.postForEntity(BASE_URL, modelTemplate, ModelTemplate.class);
        return response.getBody();
    }

    public void putModelTemplate(long id, ModelTemplate modelTemplate) { restTemplate.put(BASE_URL+"/"+id, modelTemplate); }

    public void deleteModelTemplate(long id) {
//        cascading (otherModelTemplate)
//        List<OtherModelTemplate> otherModelTemplateList = otherModelTemplateService.getAllOtherModelTemplates();
//        for(OtherModelTemplate otherModelTemplate: otherModelTemplateList) {
//            if (otherModelTemplate.getIdModelTemplate() == id) {
//                otherModelTemplateService.deleteOtherModelTemplateById(otherModelTemplate.getId());
//            }
//        }
        restTemplate.delete(BASE_URL+"/"+id);
    }
}
