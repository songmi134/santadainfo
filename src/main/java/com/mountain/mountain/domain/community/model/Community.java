package com.mountain.mountain.domain.community.model;


import com.mountain.mountain.controller.community.dto.RegisterCommuDTO;
import com.mountain.mountain.domain.category.model.Category;
import com.mountain.mountain.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commupostNo;

    @OneToOne
    @JoinColumn(name = "write_id")
    private User writerId;

    @OneToOne
    @JoinColumn(name = "cate_id")
    private Category cateId;

    @Column(nullable = false, name = "content")
    private String content;

    @Column(name = "fst_reg_dtm")
    @CreatedDate
    private LocalDateTime fstRegDtm;

    @Column(name = "lst_reg_dtm")
    @UpdateTimestamp
    private LocalDateTime lstUpdDtm;

    @Column(name = "view_count")
    private Long viewCount = 0L;

    @Column(nullable = false,name = "title")
    private String title;

    @Builder
    public Community(Long commupostNo, User writerId, Category cateId, String content, LocalDateTime fstRegDtm, LocalDateTime lstUpdDtm, Long viewCount, String title) {
        this.commupostNo = commupostNo;
        this.writerId = writerId;
        this.cateId = cateId;
        this.content = content;
        this.fstRegDtm = fstRegDtm;
        this.lstUpdDtm = lstUpdDtm;
        this.viewCount = viewCount;
        this.title = title;
    }



    public void updatedCommunity(Community updatedGame) {
        if(updatedGame.getCateId() != null) cateId = updatedGame.getCateId();
        if(updatedGame.getTitle() != null) title = updatedGame.getTitle();
        if(updatedGame.getContent() != null) content = updatedGame.getContent();
        if(updatedGame.getWriterId() != null) writerId = updatedGame.getWriterId();
        if(updatedGame.getViewCount() != null) viewCount = updatedGame.getViewCount();
        if(updatedGame.getFstRegDtm() != null) fstRegDtm = updatedGame.getFstRegDtm();
        if(updatedGame.getLstUpdDtm() == null) lstUpdDtm = updatedGame.getLstUpdDtm();
        if(updatedGame.getCommupostNo() != null) commupostNo = updatedGame.getCommupostNo();
    }
}
