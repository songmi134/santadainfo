package com.mountain.mountain.controller.community.specification;

import com.mountain.mountain.domain.community.model.Community;
import org.springframework.data.jpa.domain.Specification;

public class CommunitySpecification {


    public static Specification<Community> equalCateId(Long cateId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cateId").get("id"), cateId));
        //root - 제너릭에들어간 Community 객체 가리킴 , get으로 longtype 맞을때까지 꺼내올 수 있음
    }

    public static Specification<Community> likeTitle(String title) {
        return (((root, query, criteriaBuilder) -> criteriaBuilder.like(root.<String>get("title"), "%"+title+"%")));
    }

    public static Specification<Community> commupostNo(Long commupostNo) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("commupostNo").get("id"),commupostNo));
    }
}
