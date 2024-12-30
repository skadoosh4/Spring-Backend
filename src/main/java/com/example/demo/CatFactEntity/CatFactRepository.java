package com.example.demo.CatFactEntity;

import com.example.demo.catfact.model.CatFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatFactRepository extends JpaRepository<CatFactEntity , Integer> {
}
