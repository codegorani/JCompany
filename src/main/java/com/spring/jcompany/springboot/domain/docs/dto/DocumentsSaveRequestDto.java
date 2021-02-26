package com.spring.jcompany.springboot.domain.docs.dto;

import com.spring.jcompany.springboot.domain.docs.Documents;
import com.spring.jcompany.springboot.domain.docs.DocumentsStatus;
import com.spring.jcompany.springboot.domain.docs.DocumentsType;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DocumentsSaveRequestDto {
    private String title;
    private String content;
    private DocumentsType documentsType;
    private Long userId;
    private Long approvalId;

    @Builder
    public DocumentsSaveRequestDto(String title, String content, DocumentsType documentsType,
                                   Long userId, Long approvalId) {
        this.title = title;
        this.content = content;
        this.documentsType = documentsType;
        this.userId = userId;
        this.approvalId = approvalId;
    }

    public Documents toEntity(User user, User approval) {
        return Documents.builder()
                .user(user)
                .title(title)
                .content(content)
                .documentsType(documentsType)
                .approval(approval)
                .draftDate(LocalDateTime.now())
                .documentsStatus(DocumentsStatus.WAITING)
                .build();
    }
}
