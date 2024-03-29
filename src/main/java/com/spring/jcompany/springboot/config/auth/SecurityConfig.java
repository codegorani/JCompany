package com.spring.jcompany.springboot.config.auth;

import com.spring.jcompany.springboot.domain.user.Role;
import com.spring.jcompany.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@SpringBootConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/user/forgot/password", "/forgot/**", "/css/**", "/js/**", "/images/**",
                        "/", "/signup", "/api/ip", "/emailValid", "/swagger-ui.html",  "/v2/**", "/configuration/ui",
                        "/swagger-resources/**", "/configuration/security", "/webjars/**", "/swagger**", "/inactive/**").permitAll()
                .antMatchers("/api/v1/**")
                .hasAnyRole(Role.USER.name(), Role.ADMIN.name(), Role.USER_MANAGER.name(),Role.DEVELOPER.name())
                .antMatchers("/admin/**").hasAnyRole(Role.ADMIN.name(), Role.DEVELOPER.name())
                .antMatchers("/manager/**")
                .hasAnyRole(Role.ADMIN.name(), Role.DEVELOPER.name(), Role.USER_MANAGER.name())
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login/error")
                .permitAll();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);

        http.userDetailsService(userService);


    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}
