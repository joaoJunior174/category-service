package com.raiadrogasil.categoryadmin.model;

import com.raiadrogasil.categoryadmin.dto.CategoryDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_category")
@Getter @Setter @NoArgsConstructor
public class Category {

    public Category(CategoryDto categoryDto) {
        this.sessionToken = categoryDto.getSession_token();
        this.sku = categoryDto.getSku();
        this.level1 = categoryDto.getLevel1();
        this.level2 = categoryDto.getLevel2();
        this.level3 = categoryDto.getLevel3();
        this.level4 = categoryDto.getLevel4();
        this.path = categoryDto.getPath();
        this.to = categoryDto.getTo();
        this.removeFrom = categoryDto.getRemoveFrom();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_token")
    private String sessionToken;

    private String sku;

    private String level1;

    private String level2;

    private String level3;

    private String level4;

    @Column(name = "category_path")
    private String path;

    @Column(name = "category_to")
    private String to;

    @Column(name = "remove_from")
    private String removeFrom;
}
