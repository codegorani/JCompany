package com.spring.jcompany.springboot.service.bulletin;

import com.spring.jcompany.springboot.domain.bulletin.Bulletin;
import com.spring.jcompany.springboot.domain.bulletin.BulletinRepository;
import com.spring.jcompany.springboot.domain.bulletin.BulletinRepositorySupport;
import com.spring.jcompany.springboot.domain.bulletin.dto.BulletinInfoResponseDto;
import com.spring.jcompany.springboot.domain.bulletin.dto.BulletinSaveRequestDto;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void bulletinDeleteService(Long bulletinId) {
        Bulletin bulletin = bulletinRepository.findById(bulletinId)
                .orElseThrow(() -> new IllegalArgumentException("Bulletin Not Found"));
        bulletinRepository.delete(bulletin);
    }

    @Transactional
    public BulletinInfoResponseDto bulletinInfoResponseService(Long bulletinId, Long userId) {
        Bulletin bulletin = bulletinRepository.findById(bulletinId)
                .orElseThrow(() -> new IllegalArgumentException("Bulletin Not Found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        String[] likeIds = bulletin.getLikeUserList().split(",");
        boolean isLiked = false;
        for (String id : likeIds) {
            if (user.getId().equals(Long.parseLong(id))) {
                isLiked = true;
            }
        }
        return new BulletinInfoResponseDto(bulletin).setLiked(isLiked);
    }

    @Transactional
    public List<BulletinInfoResponseDto> bulletinListResponseDto(Long userId) {
        List<Bulletin> bulletinList = bulletinRepository.findAll();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        List<BulletinInfoResponseDto> returnList = new ArrayList<>();
        for (Bulletin bulletin : bulletinList) {
            String[] likeIds = bulletin.getLikeUserList().split(",");
            boolean isLiked = false;
            for (String id : likeIds) {
                if (user.getId().equals(Long.parseLong(id))) {
                    isLiked = true;
                }
            }
            returnList.add(new BulletinInfoResponseDto(bulletin).setLiked(isLiked));
        }
        return returnList;
    }

    @Transactional
    public void bulletinLikeCountUpService(Long userId, Long bulletinId) {
        Bulletin bulletin = bulletinRepository.findById(bulletinId)
                .orElseThrow(() -> new IllegalArgumentException("Bulletin Not Found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        bulletinRepository.save(bulletin.likeUp(user));
    }

    @Transactional
    public void bulletinLikeCountDownService(Long userId, Long bulletinId) {
        Bulletin bulletin = bulletinRepository.findById(bulletinId)
                .orElseThrow(() -> new IllegalArgumentException("Bulletin Not Found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        bulletinRepository.save(bulletin.likeDown(user));
    }
}
