package com.spring.jcompany.springboot.domain.bulletin.dto;

import com.spring.jcompany.springboot.domain.bulletin.Bulletin;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BulletinListResponseDto {
    private String bulletinTitle;
    private String bulletinContent;
    private int likeCount;
    private LocalDateTime createdDate;
    private User writer;
    private boolean isLiked;

    public BulletinListResponseDto(Bulletin entity) {
        this.bulletinTitle = entity.getBulletinTitle();
        this.bulletinContent = entity.getBulletinContent();
        this.likeCount = entity.getLikeCount();
        this.createdDate = entity.getCreatedDate();
        this.writer = entity.getWriter();
    }

    public BulletinListResponseDto setLiked(boolean isLiked) {
        this.isLiked = isLiked;
        return this;
    }
}
