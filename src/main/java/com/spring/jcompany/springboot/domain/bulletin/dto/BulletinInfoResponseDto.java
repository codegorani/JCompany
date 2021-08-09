package com.spring.jcompany.springboot.domain.bulletin.dto;

import com.spring.jcompany.springboot.domain.bulletin.Bulletin;
import lombok.Getter;

@Getter
public class BulletinInfoResponseDto {
    private String bulletinTitle;
    private String bulletinContent;
    private int likeCount;
    private boolean isLiked;

    public BulletinInfoResponseDto(Bulletin entity) {
        this.bulletinTitle = entity.getBulletinTitle();
        this.bulletinContent = entity.getBulletinContent();
        this.likeCount = entity.getLikeCount();
    }

    public BulletinInfoResponseDto setLiked(boolean isLiked) {
        this.isLiked = isLiked;
        return this;
    }
}
