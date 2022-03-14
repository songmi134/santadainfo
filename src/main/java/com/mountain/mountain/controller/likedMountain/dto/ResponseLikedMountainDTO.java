package com.mountain.mountain.controller.likedMountain.dto;


import com.mountain.mountain.domain.likedmountain.model.Likedmountain;
import com.mountain.mountain.domain.mountain.model.Mountain;
import lombok.Data;


@Data
public class ResponseLikedMountainDTO {

    private Long id;

    private Mountain mountain;



    public ResponseLikedMountainDTO(Likedmountain likedmountain) {
        this.id = likedmountain.getId();
        this.mountain = likedmountain.getMountainNo();
    }
}
