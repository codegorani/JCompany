package com.spring.jcompany.springboot.domain.bulletin;

import com.spring.jcompany.springboot.domain.bulletin.dto.BulletinUpdateRequestDto;
import com.spring.jcompany.springboot.domain.global.BaseTimeEntity;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Entity
public class Bulletin extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bulletinTitle;

    @Column
    private String bulletinContent;

    @Column
    private String attachmentFilePath;

    @Column
    @Enumerated(EnumType.STRING)
    private BulletinCategory bulletinCategory;

    @ManyToOne
    @JoinColumn
    private User writer;

    @Column
    private int likeCount;

    @Column
    private String likeUserList;

    @Builder
    public Bulletin(String bulletinTitle, String bulletinContent, String attachmentFilePath,
                    BulletinCategory bulletinCategory, User writer, int likeCount, String likeUserList) {
        this.bulletinTitle = bulletinTitle;
        this.bulletinContent = bulletinContent;
        this.attachmentFilePath = attachmentFilePath;
        this.bulletinCategory = bulletinCategory;
        this.writer = writer;
        this.likeCount = likeCount;
        this.likeUserList = likeUserList;
    }

    public Bulletin likeUp(User user) {
        if (this.likeUserList.equals("")) {
            this.likeUserList += user.getId();
        } else {
            String[] userIdList = this.likeUserList.split(",");
            for (String id : userIdList) {
                if (id.equals(String.valueOf(user.getId()))) {
                    return likeDown(user);
                }
            }
            this.likeUserList += "," + user.getId();
        }
        this.likeCount += 1;
        return this;
    }

    public Bulletin likeDown(User user) {
        List<Long> userList = Arrays.stream(this.likeUserList.split(",")).map(Long::parseLong)
                .collect(Collectors.toList());
        StringBuilder result = new StringBuilder();

        userList.remove(user.getId());
        for(Long ids : userList) {
            if (result.toString().equals("")) {
                result.append(ids);
            } else {
                result.append(",").append(user.getId());
            }
        }
        this.likeUserList = result.toString();
        this.likeCount -= 1;
        return this;
    }

    public Bulletin setLikeUserList(String likeUserList) {
        this.likeUserList = likeUserList;
        return this;
    }

    public Bulletin bulletinUpdateByDto(BulletinUpdateRequestDto requestDto) {
        this.bulletinTitle = requestDto.getBulletinTitle();
        this.bulletinContent = requestDto.getBulletinContent();
        this.attachmentFilePath = requestDto.getAttachmentFilePath();
        this.bulletinCategory = requestDto.getBulletinCategory();
        return this;
    }
}
