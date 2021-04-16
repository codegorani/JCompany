package com.spring.jcompany.springboot.web.api.docs;

import com.spring.jcompany.springboot.domain.docs.dto.DocumentsSaveRequestDto;
import com.spring.jcompany.springboot.service.docs.DocumentsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DocumentsApiController {
    private final DocumentsService documentsService;

    @ApiOperation("Save the Documents")
    @PostMapping("/api/v1/docs")
    public Long documentsSaveRequestControl(@RequestBody DocumentsSaveRequestDto requestDto) {
        return documentsService.documentsSaveService(requestDto);
    }

    @PostMapping("/api/v1/docs/confirm/{id}")
    public void documentsConfirmControl(@PathVariable("id") Long id) {
        documentsService.documentsConfrimService(id);
    }
}
