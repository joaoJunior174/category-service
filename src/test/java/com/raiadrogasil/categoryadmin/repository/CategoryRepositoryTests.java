package com.raiadrogasil.categoryadmin.repository;

import com.raiadrogasil.categoryadmin.factory.CategoryDtoListFactory;
import com.raiadrogasil.categoryadmin.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTests {

    private static String tokenExist;
    private static String tokenDoesNotExist;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeAll
    static void setup() {
        tokenExist = "S";
        tokenDoesNotExist = "N";
    }
    @Test
    public void findShouldFindCategoryBySessionTokenWhenItPassd() {
        List<Category> categoryList = categoryRepository.findBySessionToken(tokenExist);
        CategoryDtoListFactory categoryDtoListFactory = new CategoryDtoListFactory(categoryList);
        Assertions.assertEquals(2, categoryDtoListFactory.getCategoryDtoList().size());
    }

    @Test
    public void findShouldNotFindCategoryBySessionTokenWhenItNotExist() {
        List<Category> categoryList = categoryRepository.findBySessionToken(tokenDoesNotExist);
        CategoryDtoListFactory categoryDtoListFactory = new CategoryDtoListFactory(categoryList);
        Assertions.assertNotEquals(2, categoryDtoListFactory.getCategoryDtoList().size());
    }
}
