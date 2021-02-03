package com.spring.jcompany.springboot.service.user;

import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserRepository;
import com.spring.jcompany.springboot.domain.user.dto.SessionUser;
import com.spring.jcompany.springboot.domain.user.dto.UserBasicResponseDto;
import com.spring.jcompany.springboot.domain.user.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getKey()));
        httpSession.setAttribute("user", new SessionUser(user));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
