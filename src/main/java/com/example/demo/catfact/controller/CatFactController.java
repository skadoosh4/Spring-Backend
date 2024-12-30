package com.example.demo.catfact.controller;


import com.example.demo.CatFactEntity.CatFactEntity;
import com.example.demo.CatFactEntity.CatFactRepository;
import com.example.demo.catfact.model.CatFact;
import com.example.demo.catfact.model.CatFactDTO;
import com.example.demo.catfact.service.GetCatFact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/catfact")
public class CatFactController {

    private final GetCatFact getCatFact;
    private final CatFactRepository catFactRepository;

    @Autowired
    public CatFactController(GetCatFact getCatFact , CatFactRepository catFactRepository) {
        this.getCatFact = getCatFact;
        this.catFactRepository = catFactRepository;
    }

    @GetMapping
    public ResponseEntity<CatFactDTO> getCatFact(){
        return getCatFact.execute(null);
    }

    @GetMapping("/local")
    public ResponseEntity<Iterable<CatFact>> getSavedCatFacts() {
        return ResponseEntity.ok(catFactRepository
                .findAll()
                .stream()
                .map(CatFactEntity::converToCatFact)
                .toList());
    }
}
