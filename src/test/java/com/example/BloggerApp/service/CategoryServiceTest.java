package com.example.BloggerApp.service;

import com.example.BloggerApp.http.request.CreateCategoryRequest;
import com.example.BloggerApp.repository.CategoryRepository;
import com.example.BloggerApp.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Test
    public void addCategory(){
       CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest("Test Title");
       categoryServiceImpl.addCategory(createCategoryRequest);
    }
}
