package com.mountain.mountain.domain.mountain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="mountain")
public class Mountain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mountainNo;

    @Column(name = "mountain_name")
    private String mountainName;

    @Column(name = "org_url")
    private String orgUrl;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "trans_info", columnDefinition= "TEXT")
    private String transInfo;

    @Column(name = "mountain_info" , columnDefinition= "TEXT")
    private String mountainInfo;

    @Column(name = "address_detail" , columnDefinition= "TEXT")
    private String addressDetail;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "mountain_sum" , columnDefinition= "TEXT")
    private String mountainSum;

    @Column(name = "mountain_height")
    private String mountainHeight;

}



