package com.example.clase5gtics.controller;

import com.example.clase5gtics.entity.Tecnicos;
import com.example.clase5gtics.repository.TecnicosRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tecnicos")
public class TecnicosController {
    final TecnicosRepository tecnicosRepository;
    public TecnicosController(TecnicosRepository tecnicosRepository){
        this.tecnicosRepository=tecnicosRepository;

    }


    @GetMapping("/listaTec")
    public String showIndexAdminSede(Model model){
        List<Tecnicos> lisa = tecnicosRepository.findAll();

        model.addAttribute("lisa", lisa);
        return "tecnicos/listaTec";
    }

    @GetMapping("/newFrm")
    public String nuevoTecnicoFrm(Model model, @ModelAttribute("tecnicos") Tecnicos tecnicos) {
        model.addAttribute("lista", tecnicosRepository.findAll());
        return "tecnicos/newFrm";
    }

    @PostMapping("/save")
    public String guardarTecnico(RedirectAttributes attr,
                                 Model model,
                                 @ModelAttribute("tecnicos") @Validated Tecnicos tecnicos,
                                 BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            model.addAttribute("list", tecnicosRepository.findAll());
            return "tecnicos/newFrm";
        }else{
            if (tecnicos.getTechnicianId() == 0) {
                List<Tecnicos> productList = tecnicosRepository.findByFirstName(tecnicos.getFirstName());
                boolean existe = false;
                for (Tecnicos p : productList) {
                    if (p.getFirstName().equals(tecnicos.getFirstName())) {
                        existe = true;
                        break;
                    }
                }
                if (existe) {
                    System.out.println("El tecnico existe");
                    model.addAttribute("listaCategorias", tecnicosRepository.findAll());
                    return "tecnicos/newFrm";
                } else {
                    attr.addFlashAttribute("msg", "Tecnico creado exitosamente");
                    tecnicosRepository.save(tecnicos);
                    return "redirect:/tecnicos/listaTec";
                }
            } else {
                attr.addFlashAttribute("msg", "Tecnico actualizado exitosamente");
                tecnicosRepository.save(tecnicos);
                return "redirect:/tecnicos/listaTec";
            }
        }
    }

    @GetMapping("/edit")
    public String editarTecnicos(Model model, @RequestParam("id") int id) {

        Optional<Tecnicos> optProduct = tecnicosRepository.findById(id);
        model.addAttribute("lista", tecnicosRepository.findAll());
        if (optProduct.isPresent()) {
            Tecnicos tecnicos = optProduct.get();
            model.addAttribute("tecnicos", tecnicos);

            return "tecnicos/newFrm";
        } else {
            return "redirect:/tecnicos/listTec";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(Model model,
                                      @RequestParam("id") int id,
                                      RedirectAttributes attr) {

        Optional<Tecnicos> optProduct = tecnicosRepository.findById(id);

        if (optProduct.isPresent()) {
            tecnicosRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Tecnico borrado exitosamente");
        }
        return "redirect:/tecnico";

    }


}
