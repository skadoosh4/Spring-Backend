package com.example.demo.CatFactEntity;

import com.example.demo.catfact.model.CatFact;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cat_facts")
@AllArgsConstructor
@NoArgsConstructor
public class CatFactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "catfactJSON")
    private String catFactJSON;

    public CatFactEntity(CatFact catFact) {
        this.catFactJSON = converToJSON(catFact);
    }

    //Serialization
    private String converToJSON(CatFact catFact) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(catFact);
        } catch (Exception e) {
            throw new RuntimeException("JSON parsing error");
        }
    }

    //Deserialization
    public CatFact converToCatFact() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(catFactJSON, CatFact.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON parsing error");
        }
    }

}
