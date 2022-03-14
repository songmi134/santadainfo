package com.mountain.mountain.domain.community.service;

import com.mountain.mountain.controller.community.dto.RegisterCommuDTO;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.category.service.CategoryService;
import com.mountain.mountain.domain.comment.model.Comment;
import com.mountain.mountain.domain.community.dao.CommunityRepository;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.user.dao.UserRepository;
import com.mountain.mountain.domain.user.model.User;
import com.mountain.mountain.exception.CustomException;
import com.mountain.mountain.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommunityService {

    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public Community registerCommunity(RegisterCommuDTO registerCommuDTO ,User user ) {

        Category category = categoryService.findCateByNo(registerCommuDTO);

        Community community = Community.builder()
                .writerId(user)
                .title(registerCommuDTO.getTitle())
                .cateId(category)
                .content(registerCommuDTO.getContent())
                .viewCount(0L)
                .build();

        return communityRepository.save(community);
    }



    @Transactional
    public Page<Community> findAll(Specification<Community> spec, Pageable pageable) {
        Page<Community> communities = communityRepository.findAll(spec, pageable);

        if (communities.isEmpty())
            throw new CustomException(ErrorCode.NOT_FOUND_COMMUNITY);

        return communities;
    }

    @Transactional
    public Page<Community> findAllUserCommunity(String userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow();// TODO: UserNotFoundException::new 추가하기

        Page<Community> communities = communityRepository.findByWriterId(user, pageable);

        if (communities.isEmpty())
            throw new CustomException(ErrorCode.NOT_FOUND_COMMUNITY);

        return communities;
    }

    @Transactional
    public void deleteCommunity(User user, Long commupostNo) {
        Community commu = communityRepository.findById(commupostNo)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY));

        if(commu.getWriterId().getId().equals(user.getId())) {
            communityRepository.delete(commu);
        } else {
            throw new CustomException(ErrorCode.FORBIDDEN_USER);
        }
    }



    @Transactional
    public Community findCommunityByNo(Long commupostNo) {

        Community commu = communityRepository.findById(commupostNo)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_COMMUNITY));


            commu.setViewCount(commu.getViewCount()+1L);
            communityRepository.save(commu);
            return commu;
    }

    @Transactional
    public void updateCommunity(User user, Long commupostNo, RegisterCommuDTO registerCommuDTO) {
        Optional<Community> community = communityRepository.findById(commupostNo);

        Category category = categoryService.findCateByNo(registerCommuDTO);

        if(community.isPresent()) {

            Community seletedCommunity = community.get();

            seletedCommunity.setWriterId(community.get().getWriterId());
            seletedCommunity.setTitle(registerCommuDTO.getTitle());
            seletedCommunity.setContent(registerCommuDTO.getContent());
            seletedCommunity.setViewCount(community.get().getViewCount());
            seletedCommunity.setCommupostNo(community.get().getCommupostNo());
            seletedCommunity.setFstRegDtm(community.get().getFstRegDtm());
            seletedCommunity.setLstUpdDtm(community.get().getLstUpdDtm());
            seletedCommunity.setCateId(category);

            communityRepository.save(seletedCommunity);
        }

    }


}

