package com.example.BloggerApp.service.impl;

import com.example.BloggerApp.http.request.CreateCategoryRequest;
import com.example.BloggerApp.http.request.UpdateCategoryRequest;
import com.example.BloggerApp.http.response.GetCategoryResponse;
import com.example.BloggerApp.models.CategoryEntity;
import com.example.BloggerApp.repository.CategoryRepository;
import com.example.BloggerApp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;


    public GetCategoryResponse addCategory(CreateCategoryRequest createCategoryRequest){
        CategoryEntity categoryEntity = modelMapper.map(createCategoryRequest, CategoryEntity.class);
        return modelMapper.map(categoryRepository.save(categoryEntity),GetCategoryResponse.class);
    }

    public GetCategoryResponse getCategoryById(long id){
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
        if(categoryEntity==null){
            throw new RuntimeException("No Category Exist with this id:"+id);
        }
        return modelMapper.map(categoryEntity.get(),GetCategoryResponse.class);
    }

    public List<GetCategoryResponse> getAllCategories(int pageNumber,int limit){
        Pageable pageable = PageRequest.of(pageNumber,limit);
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll(pageable).getContent();
        return categoryEntityList.stream().map(e -> modelMapper.map(e,GetCategoryResponse.class)).collect(Collectors.toList());
    }

    @Override
    public GetCategoryResponse updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        CategoryEntity categoryEntity = categoryRepository.findById(updateCategoryRequest.getId()).orElseThrow(() -> new RuntimeException("Category not found"));
        categoryEntity.setTitle(updateCategoryRequest.getTitle());
        CategoryEntity updatedCategory = categoryRepository.save(categoryEntity);
        return modelMapper.map(updatedCategory,GetCategoryResponse.class);
    }

    @Override
    public void deleteCategoryById(Long id) {
       categoryRepository.deleteById(id);
    }
}
