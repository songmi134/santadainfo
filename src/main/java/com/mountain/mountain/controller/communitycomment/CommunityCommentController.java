package com.mountain.mountain.controller.communitycomment;


import com.mountain.mountain.controller.communitycomment.dto.RegisterCommuCommentDTO;
import com.mountain.mountain.controller.communitycomment.dto.ResponseCommuCommentDTO;
import com.mountain.mountain.domain.category.dao.CategoryRepository;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.category.service.CategoryService;
import com.mountain.mountain.domain.comment.model.Comment;
import com.mountain.mountain.domain.comment.service.CommentService;
import com.mountain.mountain.domain.community.dao.CommunityRepository;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.community.service.CommunityService;
import com.mountain.mountain.domain.user.model.User;
import com.mountain.mountain.exception.CustomException;
import com.mountain.mountain.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/communities")
public class CommunityCommentController {


    @Autowired
    CommentService commentService;

    @Autowired
    CommunityService communityService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CommunityRepository communityRepository;

    // 댓글 등록
    @PostMapping("/{commuPostNum}/comments")
    public ResponseCommuCommentDTO createComment (
            @PathVariable(value = "commuPostNum") Long commuPostNum,
            @RequestBody RegisterCommuCommentDTO registerCommentDTO,
            Authentication authentication) {

        User user = ((User) authentication.getPrincipal());
        Community community = communityService.findCommunityByNo(commuPostNum);
        
        return new ResponseCommuCommentDTO(commentService.createComment(user, community,registerCommentDTO),community);

    }

    // 댓글 조회
    @GetMapping("/{commuPostNum}/comments")
    public Page<ResponseCommuCommentDTO> findComments(
            @PathVariable(value = "commuPostNum") Long commuPostNum,
            Pageable pageable) {

        Community community = communityService.findCommunityByNo(commuPostNum);

        return commentService.getCommunityCommentList(community,pageable).map(comment -> new ResponseCommuCommentDTO(comment,community));


    }

    // 댓글 삭제
    @DeleteMapping("/{commuPostNum}/comments/{commentNo}")
    public void deleteComment(
            @PathVariable(value = "commuPostNum") Long commuPostNum,
            @PathVariable(value = "commentNo") Long commentNo,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        commentService.deleteCommunityComment( user,commuPostNum,commentNo);

    }



    // 댓글 수정
    @PatchMapping("/{commuPostNum}/comments/{commentNo}")
    public void updateCommunityComment (
            @PathVariable(value = "commuPostNum") Long commuPostNum,
            @PathVariable(value = "commentNo") Long commentNo,
            @RequestBody RegisterCommuCommentDTO registerCommentDTO,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        commentService.updateCommunityComment( user, commuPostNum, commentNo,registerCommentDTO);

    }

}
