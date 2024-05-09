package com.example.clase5gtics.controller;

import com.example.clase5gtics.entity.Product;
import com.example.clase5gtics.repository.CategoryRepository;
import com.example.clase5gtics.repository.ProductRepository;
import com.example.clase5gtics.repository.SupplierRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    final ProductRepository productRepository;
    final SupplierRepository supplierRepository;
    final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, SupplierRepository supplierRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping(value = {"", "/"})
    public String listaProductos(Model model) {
        model.addAttribute("listaProductos", productRepository.findAll());
        return "product/list";
    }

    @GetMapping("/new")
    public String nuevoProductoFrm(Model model) {
        model.addAttribute("listaCategory", categoryRepository.findAll());
        model.addAttribute("listaSupplier", supplierRepository.findAll());
        return "product/newFrm";
    }

    @PostMapping("/save")
    public String guardarProducto(Product product) {
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/edit")
    public String editarTransportista(Model model, @RequestParam("id") int id) {

        Optional<Product> optProduct = productRepository.findById(id);

        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            model.addAttribute("product", product);
            model.addAttribute("listaCategory", categoryRepository.findAll());
            model.addAttribute("listaSupplier", supplierRepository.findAll());
            return "product/editFrm";
        } else {
            return "redirect:/product";
        }
    }

    @GetMapping("/delete")
    public String borrarTransportista(@RequestParam("id") int id) {

        Optional<Product> optProduct = productRepository.findById(id);

        if (optProduct.isPresent()) {
            productRepository.deleteById(id);
        }
        return "redirect:/product";

    }

}
