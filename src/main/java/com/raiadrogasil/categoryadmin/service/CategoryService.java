package com.raiadrogasil.categoryadmin.service;

import com.raiadrogasil.categoryadmin.dto.CategoryDto;
import com.raiadrogasil.categoryadmin.model.Category;
import com.raiadrogasil.categoryadmin.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    public List<CategoryDto> getCategoriesBySessionToken(String sessionToken) {
        List<Category> categoryList = categoryRepository.findBySessionToken(sessionToken);
        List<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();

        categoryList.forEach(c -> {
            CategoryDto cat = new CategoryDto(c);
            categoryDtoList.add(cat);
        });

        return categoryDtoList;
    }

    public void saveAll(List<CategoryDto> categoriesDto) throws Exception {

        throw new Exception("Erro ao processar mensagem");

    }

    public void saveAllWithNoErrors(List<CategoryDto> categoriesDto) {
        List<Category> categoryList = new ArrayList<Category>();

        categoriesDto.forEach(c -> {
            Category cat = new Category(c);
            categoryList.add(cat);
        });
        categoryRepository.saveAll(categoryList);

    }
}
