package com.raiadrogasil.categoryadmin.dto;

import com.raiadrogasil.categoryadmin.model.Category;
import lombok.Data;

@Data
public class CategoryDto {

    public CategoryDto() {}
    public CategoryDto(Category category) {
        this.session_token = category.getSessionToken();
        this.sku = category.getSku();
        this.level1 = category.getLevel1();
        this.level2 = category.getLevel2();
        this.level3 = category.getLevel3();
        this.level4 = category.getLevel4();
        this.path = category.getPath();
        this.to = category.getTo();
        this.removeFrom = category.getRemoveFrom();
    }

    private String session_token;

    private String sku;
    private String level1;
    private String level2;
    private String level3;
    private String level4;
    private String path;
    private String to;
    private String removeFrom;
}
