package com.example.BloggerApp.service;

import com.example.BloggerApp.http.request.CreateCategoryRequest;
import com.example.BloggerApp.http.response.GetCategoryResponse;
import com.example.BloggerApp.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void addCategory(){
       CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest("Test Title");
       categoryService.addCategory(createCategoryRequest);
    }
}
