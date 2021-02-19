package com.spring.jcompany.springboot.service.docs;

import com.spring.jcompany.springboot.domain.docs.DocumentsRepository;
import com.spring.jcompany.springboot.domain.user.Role;
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
}
