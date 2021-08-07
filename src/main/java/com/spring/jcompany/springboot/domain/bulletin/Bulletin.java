package com.spring.jcompany.springboot.domain.bulletin;

import com.spring.jcompany.springboot.domain.global.BaseTimeEntity;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.StringTokenizer;

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
        this.likeCount += 1;
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

        return this;
    }

    public Bulletin likeDown(User user) {
        this.likeCount -= 1;
        String[] userList = this.likeUserList.split(",");
        StringBuilder result = new StringBuilder();

        for(String ids : userList) {
            if (!ids.equals(String.valueOf(user.getId()))) {
                if (result.toString().equals("")) {
                    result.append(ids);
                } else {
                    result.append(",").append(user.getId());
                }
            }
        }
        this.likeUserList = result.toString();

        return this;
    }
}
