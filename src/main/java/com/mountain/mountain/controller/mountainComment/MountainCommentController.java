package com.mountain.mountain.controller.mountainComment;


import com.mountain.mountain.controller.communitycomment.dto.ResponseCommuCommentDTO;
import com.mountain.mountain.controller.mountainComment.dto.RegisterMTCommentDTO;
import com.mountain.mountain.controller.mountainComment.dto.ResponseMTCommentDTO;
import com.mountain.mountain.domain.comment.model.Comment;
import com.mountain.mountain.domain.comment.service.CommentService;
import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.mountain.model.Mountain;
import com.mountain.mountain.domain.mountain.service.MountainService;
import com.mountain.mountain.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mountains")
public class MountainCommentController {


    @Autowired
    CommentService commentService;

    @Autowired
    MountainService mountainService;


    // MT 댓글작성
    @PostMapping("/{mountainNo}/comments")
    public ResponseMTCommentDTO createMTComment (@PathVariable(value = "mountainNo")Long mountainNo,
                                                 @RequestBody RegisterMTCommentDTO registerMTCommentDTO,
                                                 Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return new ResponseMTCommentDTO(commentService.createMTComment(user, mountainNo, registerMTCommentDTO));

    }

    // MT 댓글 삭제
    @DeleteMapping("/{mountainNo}/comments/{commentNo}")
    public void deleteMtComment (
            @PathVariable(value = "mountainNo")Long mountainNo,
            @PathVariable(value = "commentNo")Long commentNo,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        commentService.deleteMTComment(user, mountainNo, commentNo);
    }

    // MT 댓글 수정
    @PatchMapping("/{mountainNo}/comments/{commentNo}")
    public void updateMTComment (
            @PathVariable(value = "mountainNo")Long mountainNo,
            @PathVariable(value = "commentNo")Long commentNo,
            @RequestBody RegisterMTCommentDTO registerMTCommentDTO,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        commentService.updateMTComment(user, mountainNo,commentNo,registerMTCommentDTO);

    }


    // MT 댓글 조회
    @GetMapping("/{mountainNo}/comments")
    public Page<ResponseMTCommentDTO> findComments(
            @PathVariable(value = "mountainNo") Long mountainNo,
            Pageable pageable) {

        Mountain mountain = mountainService.findMountainDetail(mountainNo);

        return commentService.getMountainCommentList(mountain,pageable).map(comment -> new ResponseMTCommentDTO(comment));


    }

    // MT 사용자 댓글 전체 조회
    @GetMapping("/users/{userId}")
    public Page<ResponseMTCommentDTO> getUserComments(
            @PathVariable("userId") String userId, Pageable pageable) {

        return commentService.findAllUserComments(userId, pageable).map(comment -> new ResponseMTCommentDTO(comment));
    }
}
