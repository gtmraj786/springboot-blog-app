package com.gtmraj.blog.service;

import com.gtmraj.blog.payload.CategoryDto;
import com.gtmraj.blog.payload.PostDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getCategories();

   CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);

   void deleteCategory(Long categoryId);

   List<PostDto> getPostsByCategory(Long categoryId);

}
