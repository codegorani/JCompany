package com.spring.jcompany.springboot.domain.docs.dto;

import com.spring.jcompany.springboot.domain.docs.Documents;
import com.spring.jcompany.springboot.domain.docs.DocumentsStatus;
import com.spring.jcompany.springboot.domain.docs.DocumentsType;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DocumentsDetailResponseDto {
    private Long id;
    private User user;
    private User approval;
    private String title;
    private String content;
    private DocumentsType documentsType;
    private DocumentsStatus documentsStatus;
    private LocalDateTime draftDate;

    public DocumentsDetailResponseDto(Documents entity) {
        this.id = entity.getId();
        this.user = entity.getUser();
        this.approval = entity.getApproval();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.documentsType = entity.getDocumentsType();
        this.documentsStatus = entity.getDocumentsStatus();
        this.draftDate = entity.getDraftDate();
    }
}
