package com.mountain.mountain.controller.category.dto;


import com.mountain.mountain.domain.category.model.Category;
import lombok.Builder;
import lombok.Data;



@Data
public class ResponseCategoryDTO {

    private Long cateId;

    private String cateName;

    public ResponseCategoryDTO(Category category) {
        this.cateId = category.getId();
        this.cateName = category.getName();
    }


}
