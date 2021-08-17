package com.spring.jcompany.springboot.domain.bulletin.dto;

import com.spring.jcompany.springboot.domain.bulletin.BulletinCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BulletinUpdateRequestDto {

    private String bulletinTitle;
    private String bulletinContent;
    private String attachmentFilePath;
    private BulletinCategory bulletinCategory;

    @Builder
    public BulletinUpdateRequestDto(String bulletinTitle, String bulletinContent,
                                    String attachmentFilePath, BulletinCategory bulletinCategory) {
        this.bulletinTitle = bulletinTitle;
        this.bulletinCategory = bulletinCategory;
        this.bulletinContent = bulletinContent;
        this.attachmentFilePath = attachmentFilePath;
    }
}
