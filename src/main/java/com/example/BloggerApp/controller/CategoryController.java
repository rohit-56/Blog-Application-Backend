package com.example.BloggerApp.controller;

import com.example.BloggerApp.http.request.CreateCategoryRequest;
import com.example.BloggerApp.http.request.UpdateCategoryRequest;
import com.example.BloggerApp.http.response.GetCategoryResponse;
import com.example.BloggerApp.service.impl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@SecurityRequirement(name = "Bearer Authentication")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<GetCategoryResponse> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest){
       return new ResponseEntity<>(categoryServiceImpl.addCategory(createCategoryRequest), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GetCategoryResponse> getCategoryById(@PathVariable("id") long id){
        return new ResponseEntity<>(categoryServiceImpl.getCategoryById(id), HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<GetCategoryResponse>> getCategoryList(){
        return new ResponseEntity<>(categoryServiceImpl.getAllCategories(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<GetCategoryResponse> updateCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest){
        return new ResponseEntity<>(categoryServiceImpl.updateCategory(updateCategoryRequest),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id){
       categoryServiceImpl.deleteCategoryById(id);
       return new ResponseEntity<>("Category Deleted",HttpStatus.OK);
    }

}
