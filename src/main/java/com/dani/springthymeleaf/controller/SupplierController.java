package com.dani.springthymeleaf.controller;

import com.dani.springthymeleaf.model.req.SupplierRequest;
import com.dani.springthymeleaf.model.res.SupplierResponse;
import com.dani.springthymeleaf.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService SupplierService;

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("supplier/index");
        List<SupplierResponse> responses = this.SupplierService.getAllSuppliers();

        modelAndView.addObject("data", responses);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("supplier/detail");
        SupplierResponse response = this.SupplierService.getSupplierById(id);
        modelAndView.addObject("supplier", response);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("supplier/add");

        modelAndView.addObject("supplier", new SupplierRequest());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("supplier") SupplierRequest request) {
        this.SupplierService.addSupplier(request);
        return new ModelAndView("redirect:/supplier");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("supplier/edit");

        SupplierResponse response = this.SupplierService.getSupplierById(id);
        modelAndView.addObject("supplier", response);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("supplier") SupplierRequest request) {
        this.SupplierService.updateSupplier(request, request.getId());
        return new ModelAndView("redirect:/supplier");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("supplier/delete");

        SupplierResponse response = this.SupplierService.getSupplierById(id);
        modelAndView.addObject("supplier", response);
        return modelAndView;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("supplier") SupplierRequest request) {
        this.SupplierService.deleteSupplier(request.getId());
        return new ModelAndView("redirect:/supplier");
    }
}
