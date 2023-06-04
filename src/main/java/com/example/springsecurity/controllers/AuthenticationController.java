package com.example.springsecurity.controllers;

import com.example.springsecurity.models.Person;
import com.example.springsecurity.models.Product;
import com.example.springsecurity.repositories.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/authentication")
    public String login(){
        return "authentication";
    }
}


//    private final CategoryRepository categoryRepository;
//
//    public AuthenticationController(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }


//    @GetMapping("admin/product/add")
//    public String addProduct(Model model){
//        model.addAttribute("product", new Product());
//        model.addAttribute("category", categoryRepository.findAll());
//        return "product/addProduct";
//    }

//import org.springframework.web.bind.annotation.GetMapping;
//
//@GetMapping("/authentication")
//    public String login(){
//        return "authentication";
//    }
//
//}
