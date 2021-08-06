package com.spring.jcompany.springboot.domain.bulletin;

import com.spring.jcompany.springboot.domain.global.BaseTimeEntity;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Builder
    public Bulletin(String bulletinTitle, String bulletinContent, String attachmentFilePath,
                    BulletinCategory bulletinCategory, User writer) {
        this.bulletinTitle = bulletinTitle;
        this.bulletinContent = bulletinContent;
        this.attachmentFilePath = attachmentFilePath;
        this.bulletinCategory = bulletinCategory;
        this.writer = writer;
    }
}
