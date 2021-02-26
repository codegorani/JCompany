package com.spring.jcompany.springboot.domain.docs.dto;

import com.spring.jcompany.springboot.domain.docs.Documents;
import com.spring.jcompany.springboot.domain.docs.DocumentsStatus;
import com.spring.jcompany.springboot.domain.docs.DocumentsType;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Getter;

@Getter
public class DocumentsListResponseDto {
    private Long id;
    private String title;
    private DocumentsType documentsType;
    private DocumentsStatus documentsStatus;
    private User user;
    private User approval;

    public DocumentsListResponseDto(Documents entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.documentsType = entity.getDocumentsType();
        this.documentsStatus = entity.getDocumentsStatus();
        this.user = entity.getUser();
        this.approval = entity.getApproval();
    }
}
