package com.mountain.mountain.controller.mountain.dto;


import com.mountain.mountain.domain.mountain.model.Mountain;
import lombok.Data;

@Data
public class ResponseMountainDTO {


    private Long mountainNo;

    private String mountainName;

    private String orgUrl;

    private Double lat;

    private Double lon;

    private String transInfo;

    private String mountainInfo;

    private String addressDetail;

    private Long likes;

    private String mountainSum;

    private String mountainHeight;

    public ResponseMountainDTO(Mountain mountain) {
        this.mountainNo = mountain.getMountainNo();
        this.mountainName = mountain.getMountainName();
        this.orgUrl = mountain.getOrgUrl();
        this.lat = mountain.getLat();
        this.lon = mountain.getLon();
        this.transInfo = mountain.getTransInfo();
        this.mountainInfo = mountain.getMountainInfo();
        this.addressDetail = mountain.getAddressDetail();
        this.likes = mountain.getLikes();
        this.mountainSum = mountain.getMountainSum();
        this.mountainHeight = mountain.getMountainHeight();
    }

}
