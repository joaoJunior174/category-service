package com.raiadrogasil.categoryadmin.service;

import com.raiadrogasil.categoryadmin.dto.CategoryDto;
import com.raiadrogasil.categoryadmin.factory.CategoryDtoListFactory;
import com.raiadrogasil.categoryadmin.model.Category;
import com.raiadrogasil.categoryadmin.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class CategoryServiceTests {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private static String existToken;
    private static String notExistToken;

    @BeforeAll
    static void setup() {
        existToken = "S";
        notExistToken = "N";
    }
    @Test
    public void getShouldGetCategoriesBySessionTokenWhenItPassed() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category());
        Mockito.doReturn(categoryList).when(categoryRepository).findBySessionToken(existToken);
        List<CategoryDto> categoryDtoList = categoryService.getCategoriesBySessionToken(existToken);
        Assertions.assertEquals(1, categoryDtoList.size());
        Mockito.verify(categoryRepository).findBySessionToken(existToken);
    }

    @Test
    public void getShouldNotGetCategoriesBySessionTokenWhenItNotPassed() {
        List<Category> categoryList = new ArrayList<>();
        Mockito.doReturn(categoryList).when(categoryRepository).findBySessionToken(notExistToken);
        List<CategoryDto> categoryDtoList = categoryService.getCategoriesBySessionToken(notExistToken);
        Assertions.assertEquals(0, categoryDtoList.size());
        Mockito.verify(categoryRepository).findBySessionToken(notExistToken);
    }

    @Test
    public void saveAllShouldThrowException() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        Assertions.assertThrows(Exception.class, () -> {
            categoryService.saveAll(categoryDtoList);
        });
    }

    @Test
    public void saveAllWithNoErrorsShouldSaveInDatabase() {
        Category categoryMock = new Category();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryDtoList.add(new CategoryDto(categoryMock));
        categoryService.saveAllWithNoErrors(categoryDtoList);
        Mockito.verify(categoryRepository).saveAll(ArgumentMatchers.any());
    }

}
