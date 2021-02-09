package com.spring.jcompany.springboot.service.admin;

import com.spring.jcompany.springboot.domain.mail.MailDto;
import com.spring.jcompany.springboot.domain.todo.board.BoardRepository;
import com.spring.jcompany.springboot.domain.todo.board.dto.BoardListResponseDto;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserRepository;
import com.spring.jcompany.springboot.domain.user.dto.AdminRequestUserResponseDto;
import com.spring.jcompany.springboot.domain.user.dto.UserSaveRequestDto;
import com.spring.jcompany.springboot.service.mail.SimpleMailService;
import com.spring.jcompany.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final UserService userService;
    private final SimpleMailService simpleMailService;

    @Transactional
    public List<AdminRequestUserResponseDto> adminFindAllUserService() {
        return userRepository.findAllDesc().stream()
                .map(AdminRequestUserResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void adminUserDeleteService(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        String path = "C:\\Users\\USER\\Documents\\GitHub\\JCompany\\src\\main\\resources\\static\\images\\user\\" + user.getEmail() + user.getBirth().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(path);
        File folder = new File(path);
        try {
            while (folder.exists()) {
                File[] folder_list = folder.listFiles();

                for (int i = 0; i < Objects.requireNonNull(folder_list).length; i++) {
                    folder_list[i].delete();
                }

                if (folder_list.length == 0 && folder.isDirectory()) {
                    folder.delete();
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        userRepository.delete(user);
    }

    @Transactional
    public Long adminUserPasswordResetService(Long id) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        /* java mail send service (not allowed)
        MailDto mailDto = new MailDto();
        mailDto.setTitle("초기화된 패스워드를 안내합니다.");
        mailDto.setMessage("초기화된 패스워드는 \"asd5689\" 입니다. 보안에 유의하십시오.");
        mailDto.setAddress(user.getEmail());
        simpleMailService.mailSend(mailDto);
         */
        user.passwordUpdate(passwordEncoder.encode("asd5689"));
        return id;
    }

    @Transactional
    public List<BoardListResponseDto> adminFindAllBoardService() {
        return boardRepository.findAllDescByAdmin().stream()
                .map(BoardListResponseDto::new).collect(Collectors.toList());
    }
}
