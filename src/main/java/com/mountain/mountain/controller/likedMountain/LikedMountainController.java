package com.mountain.mountain.controller.likedMountain;


import com.mountain.mountain.controller.likedMountain.dto.LikedMountainDTO;
import com.mountain.mountain.controller.likedMountain.dto.ResponseLikedMountainDTO;
import com.mountain.mountain.domain.likedmountain.model.Likedmountain;
import com.mountain.mountain.domain.likedmountain.service.LikedMountainService;
import com.mountain.mountain.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes/me/mountains")
public class LikedMountainController {

    @Autowired
    LikedMountainService likedMountainService;

    //산 찜하기
    @PostMapping("")
    public void likedMountain(
            @RequestBody LikedMountainDTO likedMountainDTO, Authentication authentication) {

        User user = ((User) authentication.getPrincipal());
        likedMountainService.addMountain(user,likedMountainDTO);
    }


    //찜한산 취소
    @DeleteMapping("/{mountainNo}")
    public void deleteMountain(
            @PathVariable(value = "mountainNo") Long mountainNo,
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        likedMountainService.deleteMountain(user,mountainNo);

    }

    // 좋아요 한 산 불러오기
    @GetMapping("")
    public Page<ResponseLikedMountainDTO> getLikedMountainList(Authentication authentication, Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return likedMountainService.getLikedMountainList(user,pageable).map(Likedmountain -> new ResponseLikedMountainDTO(Likedmountain));
        //map은 item을 바꿔주는 메서드
    }


}
