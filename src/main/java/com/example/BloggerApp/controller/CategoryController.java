package com.example.BloggerApp.controller;

import com.example.BloggerApp.http.request.CreateCategoryRequest;
import com.example.BloggerApp.http.response.GetCategoryResponse;
import com.example.BloggerApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<GetCategoryResponse> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest){
       return new ResponseEntity<>(categoryService.addCategory(createCategoryRequest), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GetCategoryResponse> getCategoryById(@PathVariable("id") long id){
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GetCategoryResponse>> getCategoryList(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

}
