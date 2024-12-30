package com.example.demo.catfact.service;

import com.example.demo.CatFactEntity.CatFactEntity;
import com.example.demo.CatFactEntity.CatFactRepository;
import com.example.demo.IQuery;
import com.example.demo.catfact.model.CatFact;
import com.example.demo.catfact.model.CatFactDTO;
import com.example.demo.exceptions.ExternalCatFactsDownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetCatFact implements IQuery<Void,CatFactDTO> {

    private final RestTemplate restTemplate;

    private static final String CAT_FACT_URL = "https://catfact.ninja/fact";

    private final CatFactRepository catFactRepository;

    @Autowired
    public GetCatFact(RestTemplate restTemplate , CatFactRepository catFactRepository) {
        this.restTemplate = restTemplate;
        this.catFactRepository = catFactRepository;
    }

    @Override
    public ResponseEntity<CatFactDTO> execute(Void input) {
        CatFact catFact = getCatFact();
        CatFactDTO catFactDTO = new CatFactDTO(catFact.getFact());
        return ResponseEntity.ok(catFactDTO);
    }

    private CatFact getCatFact(){
        try{
            CatFact catFact = restTemplate.getForObject(CAT_FACT_URL, CatFact.class);
            //Save to Database
            catFactRepository.save(new CatFactEntity(catFact));
            return catFact;
        }catch (Exception e){
            throw new ExternalCatFactsDownException();
        }
    }
}
