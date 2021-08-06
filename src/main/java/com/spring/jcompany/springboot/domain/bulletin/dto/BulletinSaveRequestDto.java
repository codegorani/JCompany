package com.spring.jcompany.springboot.domain.bulletin.dto;

import com.spring.jcompany.springboot.domain.bulletin.Bulletin;
import com.spring.jcompany.springboot.domain.bulletin.BulletinCategory;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BulletinSaveRequestDto {

    private String bulletinTitle;

    private String bulletinContent;

    private BulletinCategory bulletinCategory;

    public Bulletin toEntity(User user) {
        return Bulletin.builder()
                .bulletinTitle(bulletinTitle)
                .bulletinContent(bulletinContent)
                .bulletinCategory(bulletinCategory)
                .writer(user)
                .build();
    }
}
