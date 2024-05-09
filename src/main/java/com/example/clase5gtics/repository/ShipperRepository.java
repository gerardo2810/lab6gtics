package com.example.clase5gtics.repository;

import com.example.clase5gtics.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {

    List<Shipper> findByCompanyname(String nombre);

    @Query(value = "select * from shippers where CompanyName = ?1",
            nativeQuery = true)
    List<Shipper> buscarTransPorCompName(String nombre);


    @Transactional
    @Modifying
    @Query(nativeQuery = true,
    value = "update shippers set companyname = ?1 where shipperid = ?2")
    void actualizarNombreCompania(String companyName, int shipperId);


}

