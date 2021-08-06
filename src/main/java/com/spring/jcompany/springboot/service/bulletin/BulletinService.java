package com.spring.jcompany.springboot.service.bulletin;

import com.spring.jcompany.springboot.domain.bulletin.BulletinRepository;
import com.spring.jcompany.springboot.domain.bulletin.BulletinRepositorySupport;
import com.spring.jcompany.springboot.domain.bulletin.dto.BulletinSaveRequestDto;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BulletinService {
    private final BulletinRepository bulletinRepository;
    private final BulletinRepositorySupport bulletinRepositorySupport;
    private final UserRepository userRepository;

    @Transactional
    public Long bulletinSaveService(BulletinSaveRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        return bulletinRepository.save(requestDto.toEntity(user)).getId();
    }
}
