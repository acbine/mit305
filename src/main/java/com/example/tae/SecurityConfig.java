package com.example.tae;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth.requestMatchers("/","/total","/login").hasRole("USER")
                .requestMatchers("/admin").hasRole("ADMIN").anyRequest().authenticated());

        http.formLogin((auth) -> auth.loginPage("/total").loginProcessingUrl("/login")
                .defaultSuccessUrl("/total", true).successHandler((request, response, authentication) -> {
                    for(GrantedAuthority authority : authentication.getAuthorities()){
                        if(authority.getAuthority().equals("ROLE_ADMIN")) {
                            response.sendRedirect("/admin");
                            return;
                        }
                    }
                    response.sendRedirect("/total");
                }).failureHandler(((request, response, exception) -> {
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.println("<script>alert('자격 증명에 실패하였습니다.'); window.location='/total?error';</script>");
                    out.flush();
                })).permitAll());

        http.csrf((auth) -> auth.disable());
        http.logout((auth) -> auth.logoutUrl("/logout").logoutSuccessUrl("/total"));
        return http.build();
    }
}