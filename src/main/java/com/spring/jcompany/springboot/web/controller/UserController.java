package com.spring.jcompany.springboot.web.controller;

import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserStatus;
import com.spring.jcompany.springboot.domain.user.UserTeam;
import com.spring.jcompany.springboot.domain.user.dto.UserSaveRequestDto;
import com.spring.jcompany.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if(flashMap == null) {
            return "menu/login/login";
        }
        if(!flashMap.isEmpty()) {
            model.addAttribute("isDormant", true);
        }
        return "menu/login/login";
    }

    @GetMapping("/login/{email}")
    public String forgotLoginPage(@PathVariable("email") String email, Model model) {
        model.addAttribute("email", email);
        return "menu/login/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "menu/login/signup";
    }



    @GetMapping("/menu/user/{id}")
    public String userInfoPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("loginUser", userService.userInfoService(id));
        return "menu/user/user-info";
    }

    @GetMapping("/login/error")
    public String loginFailure(Model model) {
        logger.warn("login information wrong");
        model.addAttribute("isError", true);
        return "menu/login/login";
    }

    @PostMapping("/signup")
    public String userSaveRequestControl(@RequestParam String email, @RequestParam String password,
                                         @RequestParam String name, @RequestParam String birth, @RequestParam String question,
                                         @RequestParam String answer, @RequestPart MultipartFile picture, @RequestParam UserTeam userTeam, Model model) throws Exception {
        String baseUrl = "C:\\Users\\USER\\Documents\\GitHub\\JCompany\\src\\main\\resources\\static\\images\\user\\";
        String userDir = email + birth;
        String storedUrl = baseUrl + userDir;
        String filePath = "none";
        if (!Objects.equals(picture.getOriginalFilename(), "")) {
            File pathAsFile = new File(storedUrl);
            if (!Files.exists(Paths.get(storedUrl))) {
                pathAsFile.mkdir();
            }
            filePath = storedUrl + "\\" + picture.getOriginalFilename();
            picture.transferTo(new File(filePath));
            logger.info("file has stored at " + filePath);
        }
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .email(email).password(password).name(name)
                .birth(LocalDateTime.of(Integer.parseInt(birth.substring(0, 4)),
                        Integer.parseInt(birth.substring(4, 6)),
                        Integer.parseInt(birth.substring(6)), 0, 0, 0))
                .question(question).answer(answer).userTeam(userTeam).picture(filePath).build();
        userService.userSaveService(requestDto);
        return "redirect:/";
    }

    @GetMapping("/menu/user/pw/{id}")
    public String userPasswordChangePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userId", userService.findUserId(id));
        return "menu/user/user-password";
    }

    @GetMapping("/forgot")
    public String userForgotPage() {
        return "menu/user/forgot";
    }

    @GetMapping("/forgot/email")
    public String userEmailForgotPage() {
        return "menu/user/forgot-email";
    }

    @GetMapping("/forgot/password")
    public String userPasswordForgotPage() {
        return "menu/user/forgot-password";
    }

    @GetMapping("/forgot/password/page/{email}")
    public String userPasswordForgotChangePage(@PathVariable("email") String email, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("status", userService.findUserByEmail(email).getStatus());
        return "menu/user/forgot-password-change";
    }

    @GetMapping("/logout/dormant")
    public RedirectView logoutDormant(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        attributes.addFlashAttribute("isDormant", true);
        return new RedirectView("/login", true);
    }

    @GetMapping("/inactive/{id}")
    public String userInactiveRelease(@PathVariable("id") Long id, Model model) {
        if(userService.findUserById(id).getStatus().equals(UserStatus.WAITING)) {
            return "redirect:/inactive/reset/" + id;
        }
        model.addAttribute("userId", id);
        return "menu/login/inactive-login";
    }

    @GetMapping("/inactive/reset/{id}")
    public String inactiveResetControl(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("email", user.getEmail());
        if(user.getStatus().equals(UserStatus.INACTIVE)) {
            return "redirect:/login";
        }
        return "menu/user/forgot-password-change";
    }
}
