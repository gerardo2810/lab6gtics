package com.example.clase5gtics.controller;

import com.example.clase5gtics.entity.Shipper;
import com.example.clase5gtics.repository.EmployeeRepository;
import com.example.clase5gtics.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shipper")
public class ShipperController {

    final ShipperRepository shipperRepository;
    final EmployeeRepository employeeRepository;

    public ShipperController(ShipperRepository shipperRepository, EmployeeRepository employeeRepository) {
        this.shipperRepository = shipperRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(value = {"/list", ""})
    public String listarTransportistas(Model model) {

        List<Shipper> lista = shipperRepository.findAll();
        model.addAttribute("shipperList", lista);
        model.addAttribute("empleadoPorRegion", employeeRepository.listarEmpleadosPorRegion());
        model.addAttribute("empleadosPorPais",employeeRepository.listarEmpleadosPorPais());

        shipperRepository.actualizarNombreCompania("hola2",11);

        return "shipper/list";
    }

    @GetMapping("/new")
    public String nuevoTransportistaFrm() {
        return "shipper/newFrm";
    }

    @PostMapping("/save")
    public String guardarNuevoTransportista(Shipper shipper, RedirectAttributes attr) {

        if (shipper.getShipperId() == null) {
            attr.addFlashAttribute("msg", "Transportista creado exitosamente");
        } else {
            attr.addFlashAttribute("msg", "Transportista actualizado exitosamente");
        }

        shipperRepository.save(shipper);
        return "redirect:/shipper/list";
    }

    @GetMapping("/edit")
    public String editarTransportista(Model model,
                                      @RequestParam("id") int id) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            Shipper shipper = optShipper.get();
            model.addAttribute("shipper", shipper);
            return "shipper/editFrm";
        } else {
            return "redirect:/shipper/list";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(@RequestParam("id") int id) {

        Optional<Shipper> optShipper = shipperRepository.findById(id);

        if (optShipper.isPresent()) {
            shipperRepository.deleteById(id);
        }
        return "redirect:/shipper/list";

    }

    @PostMapping("/BuscarTransportistas")
    public String buscarTransportista(@RequestParam("searchField") String searchField,
                                      Model model) {

        List<Shipper> listaTransportistas = shipperRepository.buscarTransPorCompName(searchField);
        model.addAttribute("shipperList", listaTransportistas);

        return "shipper/list";
    }


}

