package com.spring.jcompany.springboot.domain.bulletin.dto;

import com.spring.jcompany.springboot.domain.bulletin.Bulletin;
import com.spring.jcompany.springboot.domain.bulletin.BulletinCategory;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BulletinInfoResponseDto {

    private String bulletinTitle;
    private String bulletinContent;
    private String attachmentFilePath;
    private BulletinCategory bulletinCategory;
    private User writer;
    private int likeCount;
    private List<String> likeUserList;
    private boolean isLiked;

    public BulletinInfoResponseDto(Bulletin entity) {
        this.bulletinTitle = entity.getBulletinTitle();
        this.bulletinContent = entity.getBulletinContent();
        this.attachmentFilePath = entity.getAttachmentFilePath();
        this.bulletinCategory = entity.getBulletinCategory();
        this.writer = entity.getWriter();
        this.likeCount = entity.getLikeCount();
        this.likeUserList = new ArrayList<>();
    }

    public void add(User user) {
        this.likeUserList.add(user.getUserTeam() + " " + user.getName() + user.getUserLevel());
    }

    public BulletinInfoResponseDto setLiked(boolean isLiked) {
        this.isLiked = isLiked;
        return this;
    }
}
