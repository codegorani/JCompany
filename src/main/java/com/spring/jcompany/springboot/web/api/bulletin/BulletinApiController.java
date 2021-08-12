package com.spring.jcompany.springboot.web.api.bulletin;

import com.spring.jcompany.springboot.config.auth.LoginUser;
import com.spring.jcompany.springboot.domain.bulletin.dto.BulletinSaveRequestDto;
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


}
