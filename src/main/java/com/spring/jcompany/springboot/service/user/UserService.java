package com.spring.jcompany.springboot.service.user;

import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserRepository;
import com.spring.jcompany.springboot.domain.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Transactional
    public void userSaveService(UserSaveRequestDto requestDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(requestDto.toEntity());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        user.loggedIn(LocalDateTime.now());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getKey()));
        httpSession.setAttribute("user", new SessionUser(user));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Transactional
    public UserInfoResponseDto userInfoService(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        return new UserInfoResponseDto(user);
    }

    @Transactional
    public void userDeleteService(Long id) {
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

    public String isEmailValid(String email) {
        String valid;
        if(!userRepository.findByEmail(email).isPresent()) {
            System.out.println("Email Valid");
            valid = "valid";
        } else {
            System.out.println("Email Invalid");
            valid = "invalid";
        }
        return valid;
    }

    @Transactional
    public Long userPasswordChange(UserPasswordRequestDto requestDto) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        if(!encoder.matches(requestDto.getPassword(), user.getPassword())) {
            return 0L;
        }
        user.passwordUpdate(encoder.encode(requestDto.getNewPassword()));
        return user.getId();
    }

    @Transactional
    public Long findUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not Found"))
                .getId();
    }

    @Transactional
    public String findForgotEmail(UserForgotEmailRequestDto requestDto) {
        User user = userRepository.findByName(requestDto.getName())
                .orElse(null);
        if(user == null) {
            return "1";
        }
        String birth = requestDto.getBirth();
        LocalDateTime birthDay = LocalDateTime.of(Integer.parseInt(birth.substring(0, 4)),
                Integer.parseInt(birth.substring(4, 6)),
                Integer.parseInt(birth.substring(6)), 0, 0, 0);
        if(user.getBirth().equals(birthDay)) {
            return user.getEmail();
        } else {
            return "0";
        }
    }

    @Transactional
    public String findForgotPassword(UserForgotPasswordRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email Not Found"));
        if(user.getAnswer().equals(requestDto.getAnswer())) {
            return user.getEmail();
        } else {
            return "0";
        }
    }

    @Transactional
    public void userPasswordChangeAsForgot(UserForgotPasswordRecreateDto recreateDto) {
        User user = userRepository.findByEmail(recreateDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email Not Found"));
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.passwordUpdate(encoder.encode(recreateDto.getPassword()));
    }

    @Transactional
    public String isEmailExist(String email) {
        User user = userRepository.findByEmail(email)
                .orElse(null);
        if(user == null) {
            return "0";
        } else {
            return user.getQuestion();
        }
    }
}
