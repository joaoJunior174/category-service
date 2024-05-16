package com.raiadrogasil.categoryadmin.factory;

import com.raiadrogasil.categoryadmin.dto.CategoryDto;
import com.raiadrogasil.categoryadmin.model.Category;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CategoryDtoListFactory {

    public List<CategoryDto> categoryDtoList;
    public CategoryDtoListFactory(List<Category> categoryList) {
        this.categoryDtoList = new ArrayList<CategoryDto>();
        categoryList.forEach(c -> {
            CategoryDto cat = new CategoryDto(c);
            this.categoryDtoList.add(cat);
        });
    }
}
