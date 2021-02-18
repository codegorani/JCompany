package com.spring.jcompany.springboot.service.manager;

import com.spring.jcompany.springboot.domain.user.UserRepository;
import com.spring.jcompany.springboot.domain.user.UserTeam;
import com.spring.jcompany.springboot.domain.user.dto.ManagerRequestUserListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManagerService {
    private final UserRepository userRepository;

    @Transactional
    public List<ManagerRequestUserListResponseDto> findAllMyTeamMemberService(UserTeam team) {
        return userRepository.findAllByUserTeam(team).stream().map(ManagerRequestUserListResponseDto::new)
                .collect(Collectors.toList());
    }
}
