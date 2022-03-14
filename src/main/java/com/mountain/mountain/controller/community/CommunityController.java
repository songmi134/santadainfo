package com.mountain.mountain.controller.community;

import com.mountain.mountain.controller.community.dto.RegisterCommuDTO;
import com.mountain.mountain.controller.community.dto.ResponseCommuDTO;
import com.mountain.mountain.controller.community.specification.CommunitySpecification;
import com.mountain.mountain.controller.mountainComment.dto.ResponseMTCommentDTO;
import com.mountain.mountain.domain.community.dao.CommunityRepository;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.community.service.CommunityService;
import com.mountain.mountain.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/communities")
@Slf4j
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @Autowired
    CommunityRepository communityRepository;


    // 커뮤니티 글 등록
    @PostMapping("")
    public ResponseCommuDTO postCommunity(
            @RequestBody RegisterCommuDTO registerCommuDTO, Authentication authentication) {
        User user = ((User) authentication.getPrincipal());
        return new ResponseCommuDTO(communityService.registerCommunity(registerCommuDTO, user));
    }


    // 커뮤니티 글 조회
    @GetMapping("")
    public Page<ResponseCommuDTO> getCommunityList(
            @RequestParam(required = false) Long cateId, // 쿼리스트링받을 때 RequestParam , 객체담을 수 없으니 Long
            @RequestParam(required = false) String title,

            Pageable pageable) {
        log.info("cateId : " + cateId);
        log.info("title : " + title);
        Specification<Community> spec = ((root, query, criteriaBuilder) -> null);

        if(cateId != null) spec = spec.and(CommunitySpecification.equalCateId(cateId));
        if(title != null) spec = spec.and(CommunitySpecification.likeTitle(title));

        return communityService.findAll(spec, pageable).map(Community -> new ResponseCommuDTO(Community));
    }

    // 사용자 커뮤니티글 전체 조회
    @GetMapping("/user/{userId}")
    public Page<ResponseCommuDTO> getUserComments(
            @PathVariable("userId") String userId, Pageable pageable) {

        return communityService.findAllUserCommunity(userId, pageable).map(Community -> new ResponseCommuDTO(Community));
    }

    // 커뮤니티글 삭제
    @DeleteMapping("/{commupostNo}")
    public void deleteCommunity(@PathVariable(value = "commupostNo") Long commupostNo, Authentication authentication) {
        User user = ((User) authentication.getPrincipal());

        communityService.deleteCommunity(user,commupostNo);

    }

    // 커뮤니티글 상세조회
    @GetMapping("/{commupostNo}")
    public ResponseCommuDTO findCommunity ( @PathVariable(value = "commupostNo") Long commupostNo) {
        return new ResponseCommuDTO(communityService.findCommunityByNo(commupostNo));
    }



    // 커뮤니티글 수정
    @PatchMapping("/{commupostNo}")
    public void updateCommunity (
            @PathVariable(value = "commupostNo") Long commupostNo,
            @RequestBody RegisterCommuDTO registerCommuDTO,
            Authentication authentication) {

        User user = ((User) authentication.getPrincipal());
        communityService.updateCommunity(user, commupostNo , registerCommuDTO);

    }


}
