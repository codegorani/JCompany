package com.spring.jcompany.springboot.domain.docs;

import com.spring.jcompany.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private User user;

    @Column
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private DocumentsType documentsType;

    @ManyToOne
    @JoinColumn
    private User approval;

    private LocalDateTime draftDate;

    @Builder
    public Documents(User user, String title, DocumentsType documentsType, User approval, LocalDateTime draftDate) {
        this.user = user;
        this.title = title;
        this.documentsType = documentsType;
        this.approval = approval;
        this.draftDate = draftDate;
    }
}
