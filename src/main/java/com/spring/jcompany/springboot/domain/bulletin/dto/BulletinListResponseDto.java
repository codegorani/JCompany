package com.spring.jcompany.springboot.domain.bulletin.dto;

import com.spring.jcompany.springboot.domain.bulletin.Bulletin;
import lombok.Getter;

@Getter
public class BulletinListResponseDto {
    private String bulletinTitle;
    private String bulletinContent;
    private int likeCount;
    private boolean isLiked;

    public BulletinListResponseDto(Bulletin entity) {
        this.bulletinTitle = entity.getBulletinTitle();
        this.bulletinContent = entity.getBulletinContent();
        this.likeCount = entity.getLikeCount();
    }

    public BulletinListResponseDto setLiked(boolean isLiked) {
        this.isLiked = isLiked;
        return this;
    }
}
