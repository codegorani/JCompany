package com.spring.jcompany.springboot.web.api.bulletin;

import com.spring.jcompany.springboot.config.auth.LoginUser;
import com.spring.jcompany.springboot.domain.bulletin.dto.BulletinSaveRequestDto;
import com.spring.jcompany.springboot.domain.bulletin.dto.BulletinUpdateRequestDto;
import com.spring.jcompany.springboot.domain.user.dto.SessionUser;
import com.spring.jcompany.springboot.service.bulletin.BulletinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BulletinApiController {

    private final BulletinService bulletinService;

    @DeleteMapping("/bulletin/{id}")
    public void bulletinDeleteControl(@PathVariable("id") Long id) {
        bulletinService.bulletinDeleteService(id);
    }

    @PostMapping("/bulletin")
    public Long bulletinCreateControl(@LoginUser SessionUser user, @RequestBody BulletinSaveRequestDto requestDto) {
        return bulletinService.bulletinSaveService(requestDto, user.getId());
    }

    @PutMapping("/bulletin/{id}")
    public Long bulletinUpdateControl(@RequestBody BulletinUpdateRequestDto requestDto, @PathVariable("id") Long bulletinId) {
        return bulletinService.bulletinUpdateService(requestDto, bulletinId);
    }

    @GetMapping("/bulletin/like/{id}")
    public void bulletinLikeButtonControl(@PathVariable("id") Long bulletinId, @LoginUser SessionUser user) {
        bulletinService.bulletinLikeProcessService(bulletinId, user.getId());
    }
}
