package com.mountain.mountain.domain.likedmountain.model;


import com.mountain.mountain.domain.mountain.model.Mountain;
import com.mountain.mountain.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;



@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="likedmountain")
public class Likedmountain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;

    @OneToOne
    @JoinColumn(name = "mountain_no")
    private Mountain mountainNo;


    @Builder
    public Likedmountain(Long id, User user, Mountain mountainNo) {
        this.id = id;
        this.user = user;
        this.mountainNo = mountainNo;
    }
}
