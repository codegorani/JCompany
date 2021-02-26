package com.spring.jcompany.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jcompany.springboot.domain.docs.Documents;
import com.spring.jcompany.springboot.domain.docs.DocumentsRepository;
import com.spring.jcompany.springboot.domain.docs.DocumentsType;
import com.spring.jcompany.springboot.domain.docs.dto.DocumentsSaveRequestDto;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocumentSaveTest {
    @LocalServerPort
    private int port;

    @Autowired
    private DocumentsRepository documentsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void documents_save_test() throws Exception{
        String title = "spring boot application erp system organize from hjs";
        User user = userRepository.findByEmail("hjhearts@nate.com").orElseThrow(() -> new IllegalArgumentException("No"));
        User approval = userRepository.findByEmail("hy.hong@jcompany.com").orElseThrow(() -> new IllegalArgumentException("No"));
        /* Test without rest
        Documents doc = Documents.builder()
                .title(title)
                .user(user)
                .documentsType(DocumentsType.VACATION)
                .approval(approval)
                .draftDate(LocalDateTime.of(2021, 2, 19, 0, 0))
                .build();

        documentsRepository.save(doc);
        */


        String url = "http://localhost:" + port + "/api/v1/docs";

        DocumentsSaveRequestDto requestDto = DocumentsSaveRequestDto.builder()
                .title(title)
                .content("Hello")
                .documentsType(DocumentsType.VACATION)
                .userId(user.getId())
                .approvalId(approval.getId())
                .build();

        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(requestDto))).andExpect(status().isOk());

        List<Documents> documentsList = documentsRepository.findAll();
        Documents result = documentsList.get(0);
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getUser().getEmail()).isEqualTo("hjhearts@nate.com");
        assertThat(result.getApproval().getEmail()).isEqualTo("hy.hong@jcompany.com");

    }
}
