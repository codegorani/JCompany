package com.spring.jcompany.springboot.service.admin;

import com.spring.jcompany.springboot.domain.todo.board.BoardRepository;
import com.spring.jcompany.springboot.domain.todo.board.dto.BoardListResponseDto;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserRepository;
import com.spring.jcompany.springboot.domain.user.dto.AdminRequestUserResponseDto;
import com.spring.jcompany.springboot.domain.user.dto.UserSaveRequestDto;
import com.spring.jcompany.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final UserService userService;

    @Transactional
    public List<AdminRequestUserResponseDto> adminFindAllUserService() {
        return userRepository.findAllDesc().stream()
                .map(AdminRequestUserResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void adminUserDeleteService(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        userRepository.delete(user);
    }

    @Transactional
    public Long adminUserPasswordResetService(Long id) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        user.passwordUpdate(passwordEncoder.encode("asd5689"));
        return id;
    }

    @Transactional
    public List<BoardListResponseDto> adminFindAllBoardService() {
        return boardRepository.findAllDescByAdmin().stream()
                .map(BoardListResponseDto::new).collect(Collectors.toList());
    }
}
