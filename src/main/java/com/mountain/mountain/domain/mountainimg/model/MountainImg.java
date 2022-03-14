package com.mountain.mountain.domain.mountainimg.model;

import com.mountain.mountain.domain.mountain.model.Mountain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "mountain_img")
public class MountainImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="mountain_no")
    private Mountain mountainNo;

    @Column(name = "img_url")
    private String imgUrl;
}
