package com.example.clase5gtics.repository;

import com.example.clase5gtics.dto.EmpleadosPorPais;
import com.example.clase5gtics.dto.EmpleadosPorRegionDto;
import com.example.clase5gtics.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "select r.regionDescription as regiondescription, count(e.employeeid) as cantidadempleados\n" +
            "FROM region r\n" +
            "inner join territories t on (r.regionid = t.regionid)\n" +
            "inner join employeeterritories et on (t.territoryid = et.territoryid)\n" +
            "inner join employees e on (e.employeeid = et.employeeid)\n" +
            "group by r.regionid" +
            " order by cantidadempleados DESC", nativeQuery = true)
    List<EmpleadosPorRegionDto> listarEmpleadosPorRegion();

    @Query(value = "select r.regionDescription as regiondescription, count(e.employeeid) as cantidadempleados\n" +
            "FROM region r\n" +
            "inner join territories t on (r.regionid = t.regionid)\n" +
            "inner join employeeterritories et on (t.territoryid = et.territoryid)\n" +
            "inner join employees e on (e.employeeid = et.employeeid)\n" +
            "group by r.regionid" +
            " having cantidadempleados >= ?1 \n" +
            "order by cantidadempleados DESC", nativeQuery = true)
    List<EmpleadosPorRegionDto> listarEmpleadosPorRegion(int cantidadMin);

    @Query(value = "SELECT country as pais, count(*) as cantidad FROM employees GROUP BY country", nativeQuery = true)
    List<EmpleadosPorPais> listarEmpleadosPorPais();

    @Query(value = "SELECT country as pais, count(*) as cantidad FROM employees WHERE country = ?1 GROUP BY country", nativeQuery = true)
    List<EmpleadosPorPais> listarEmpleadosPorPais(String pais);



}
