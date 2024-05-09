package com.example.clase5gtics.repository;

import com.example.clase5gtics.entity.Tecnicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TecnicosRepository extends JpaRepository<Tecnicos, Integer> {
    List<Tecnicos> findByFirstName(String firstname);
}
