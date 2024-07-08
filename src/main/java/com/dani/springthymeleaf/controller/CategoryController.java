package com.dani.springthymeleaf.controller;

import com.dani.springthymeleaf.model.req.CategoryRequest;
import com.dani.springthymeleaf.model.res.CategoryResponse;
import com.dani.springthymeleaf.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ModelAndView index(){

        //langkah 1
//        List<CategoryResponse> responses = new ArrayList<>();
//        responses.add(new CategoryResponse(1L, "Makanan", "Makanan ini enak"));
//        responses.add(new CategoryResponse(2L, "Minuman", "Minuman ini enak"));
//        responses.add(new CategoryResponse(3L, "Trend", "Trend ini bagus"));

        //langkah 2
        List<CategoryResponse> categories = categoryService.getAllCategories();
        ModelAndView modelAndView = new ModelAndView("category/index");
        //ngirim data ke view
        modelAndView.addObject("data", categories);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("category/detail");
        CategoryResponse category = categoryService.getCategoryById(id);
        view.addObject("category", category);
        return view;
    }

    @GetMapping("/_add")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("category/_add");
        modelAndView.addObject("category", new CategoryRequest());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addCategory() {
        ModelAndView view = new ModelAndView("category/add");
        view.addObject("category", new CategoryRequest());
        return view;
    }
    @PostMapping("/save")
    public ModelAndView saveCategory(@ModelAttribute("category") CategoryRequest category) {
        this.categoryService.addCategory(category);
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("edit/{id}")
    public ModelAndView editCategory(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("category/edit");
        CategoryResponse categoryResponse = categoryService.getCategoryById(id);
        view.addObject("category", categoryResponse);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView updateCategory(@ModelAttribute("category") CategoryRequest category, Long id) {
        this.categoryService.updateCategory(category, id);
        return new ModelAndView("redirect:/category");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        ModelAndView view = new ModelAndView("category/delete");
        CategoryResponse categoryResponse = categoryService.getCategoryById(id);
        view.addObject("category", categoryResponse);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("category") CategoryResponse category) {
        this.categoryService.deleteCategory(category.getId());
        return new ModelAndView("redirect:/category");
    }
}
