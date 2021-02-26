package com.spring.jcompany.springboot.web.api.docs;

import com.spring.jcompany.springboot.domain.docs.dto.DocumentsSaveRequestDto;
import com.spring.jcompany.springboot.service.docs.DocumentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DocumentsApiController {
    private final DocumentsService documentsService;

    @PostMapping("/api/v1/docs")
    public Long documentsSaveRequestControl(@RequestBody DocumentsSaveRequestDto requestDto) {
        return documentsService.documentsSaveService(requestDto);
    }
}
