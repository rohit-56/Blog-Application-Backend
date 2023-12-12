package com.example.BloggerApp.service;

import com.example.BloggerApp.http.request.CreateCategoryRequest;
import com.example.BloggerApp.http.response.GetCategoryResponse;

import java.util.List;

public interface CategoryService {

    GetCategoryResponse addCategory(CreateCategoryRequest createCategoryRequest);

    public GetCategoryResponse getCategoryById(long id);
    List<GetCategoryResponse> getAllCategories();
}
