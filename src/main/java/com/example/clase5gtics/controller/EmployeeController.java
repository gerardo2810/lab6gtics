package com.example.clase5gtics.controller;

import com.example.clase5gtics.dto.EmpleadosPorRegionDto;
import com.example.clase5gtics.repository.EmployeeRepository;
import com.example.clase5gtics.repository.ShipperRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    final ShipperRepository shipperRepository;
    final EmployeeRepository employeeRepository;

    public EmployeeController(ShipperRepository shipperRepository, EmployeeRepository employeeRepository) {
        this.shipperRepository = shipperRepository;
        this.employeeRepository = employeeRepository;
    }
    @GetMapping( "/estadistica")
    public String listEmployee(Model model){
        List<EmpleadosPorRegionDto> lista = employeeRepository.listarEmpleadosPorRegion();
        model.addAttribute("list", lista);
        model.addAttribute("lista1", employeeRepository.listarEmpleadosPorPais());
        model.addAttribute("listaCompleta", employeeRepository.listarEmpleadosPorPais());
        return "employee/estadistica";
    }

    @PostMapping("/searchEmpMin")
    public String busqueda(@RequestParam("searchField") int busqueda, Model model){
        model.addAttribute("list", employeeRepository.listarEmpleadosPorRegion(busqueda));
        return "employee/estadistica";
    }

    @PostMapping("/filtroPais")
    public String filtroPais(@RequestParam("filtro") String filtro, Model model){
        List<EmpleadosPorRegionDto> lista = employeeRepository.listarEmpleadosPorRegion();
        model.addAttribute("listaCompleta", employeeRepository.listarEmpleadosPorPais());
        model.addAttribute("list", lista);
        if(filtro.equals("-1")){

            model.addAttribute("lista1", employeeRepository.listarEmpleadosPorPais());
        }else{
            model.addAttribute("lista1", employeeRepository.listarEmpleadosPorPais(filtro));
        }
        return "employee/estadistica";
    }
}
