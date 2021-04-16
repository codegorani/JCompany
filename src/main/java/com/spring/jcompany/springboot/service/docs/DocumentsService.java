package com.spring.jcompany.springboot.service.docs;

import com.spring.jcompany.springboot.domain.docs.Documents;
import com.spring.jcompany.springboot.domain.docs.DocumentsRepository;
import com.spring.jcompany.springboot.domain.docs.DocumentsStatus;
import com.spring.jcompany.springboot.domain.docs.dto.DocumentsDetailResponseDto;
import com.spring.jcompany.springboot.domain.docs.dto.DocumentsListResponseDto;
import com.spring.jcompany.springboot.domain.docs.dto.DocumentsSaveRequestDto;
import com.spring.jcompany.springboot.domain.user.Role;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserRepository;
import com.spring.jcompany.springboot.domain.user.dto.DocumentsApprovalUserResponseDto;
import com.spring.jcompany.springboot.domain.user.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DocumentsService {
    private final UserRepository userRepository;
    private final DocumentsRepository documentsRepository;

    @Transactional
    public List<DocumentsApprovalUserResponseDto> documentsApprovalListService(SessionUser user) {
        return userRepository.findAllByUserTeamAndRoleAndUserLevelIsGreaterThan(user.getUserTeam(), Role.USER_MANAGER, user.getUserLevel())
                .stream().map(DocumentsApprovalUserResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public Long documentsSaveService(DocumentsSaveRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("No User"));
        User approval = userRepository.findById(requestDto.getApprovalId()).orElseThrow(() -> new IllegalArgumentException("No Approval"));
        return documentsRepository.save(requestDto.toEntity(user, approval)).getId();
    }

    @Transactional
    public List<DocumentsListResponseDto> documentsMyDocsListService(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No User"));
        return documentsRepository.findAllByUser(user).stream()
                .map(DocumentsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public List<DocumentsListResponseDto> documentsToMeListService(Long id) {
        User approval = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No User"));
        return documentsRepository.findAllByApproval(approval).stream()
                .map(DocumentsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public DocumentsDetailResponseDto documentsToMeDetailService(Long id) {
        Documents doc = documentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Documents Not Found"));
        if(doc.getDocumentsStatus().equals(DocumentsStatus.WAITING)) {
            doc.statusUpdate(DocumentsStatus.READING);
        }
        return new DocumentsDetailResponseDto(doc);
    }

    @Transactional
    public void documentsApprovalService(Long id) {
        Documents doc = documentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Documents Not Found"));
        doc.statusUpdate(DocumentsStatus.APPROVAL_DONE);
    }

    @Transactional
    public DocumentsDetailResponseDto documentsMyDetailService(Long id) {
        Documents doc = documentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Documents Not Found"));
        return new DocumentsDetailResponseDto(doc);
    }

    @Transactional
    public void documentsConfrimService(Long id) {
        Documents doc = documentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Documents Not Found"));
        doc.statusUpdate(DocumentsStatus.APPROVAL_DONE);
    }
}
