package com.dani.springthymeleaf.controller;

import com.dani.springthymeleaf.model.res.CategoryResponse;
import com.dani.springthymeleaf.service.CategoryService;
import com.dani.springthymeleaf.model.req.ProductRequest;
import com.dani.springthymeleaf.model.res.ProductResponse;
import com.dani.springthymeleaf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CategoryService service;

    @Autowired
    private ProductService productService;

    private void addObject(ModelAndView view){
        List<CategoryResponse> categoryResponses = this.service.getAllCategories();
        view.addObject("category", categoryResponses);
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView view = new ModelAndView("product/index");

        List<ProductResponse> responses = this.productService.getAll();
        view.addObject("data", responses);
        addObject(view);
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("product/detail");

        ProductResponse product = productService.getById(id);
        view.addObject("product", product);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("product/add");
        addObject(view);
        view.addObject("product", new ProductRequest());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("product")ProductRequest request){
        this.productService.save(request);
        return new ModelAndView("redirect:/product");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("product/edit");
        addObject(view);

        ProductResponse product = productService.getById(id);
        view.addObject("product", product);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("product")ProductRequest request){
        this.productService.update(request, request.getId());
        return new ModelAndView("redirect:/product");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id){
        ModelAndView view = new ModelAndView("product/delete");
        addObject(view);

        ProductResponse product = productService.getById(id);
        view.addObject("product", product);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("product")ProductRequest request){
        this.productService.delete(request.getId());
        return new ModelAndView("redirect:/product");
    }
}
