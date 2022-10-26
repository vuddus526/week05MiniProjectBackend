package com.sparta.week05miniprojectbackend.security;


import com.sparta.week05miniprojectbackend.jwt.filter.JwtAuthFilter;
import com.sparta.week05miniprojectbackend.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer(){
        return (web) -> web.ignoring().antMatchers("/h2-console/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {

        // CORS
        http.cors().configurationSource(request -> {
                    var cors = new CorsConfiguration();
                    cors.setAllowedOriginPatterns(List.of("*"));
                    cors.setAllowedMethods(List.of("*"));
                    cors.setAllowedHeaders(List.of("*"));
//                    cors.addExposedHeader("Access_Token");
//                    cors.addExposedHeader("Refresh_Token");
                    cors.addExposedHeader("*");
//            cors.exposedHeaders("*");
                    cors.setAllowCredentials(true);
                    return cors;
        });

        http.csrf().disable();


        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
//                .antMatchers("/api/auth/**").authenticated()
//                // 로그인
//                .antMatchers(HttpMethod.POST, "/api/user/signup").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/user/login").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/issue/token").permitAll()
//
//                // 게시글
//                .antMatchers(HttpMethod.GET, "/api/posts").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/posts/{postId}").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/posts").authenticated()
//                .antMatchers(HttpMethod.PUT, "/api/posts/{postId}").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/api/posts/{postId}").authenticated()
//
//                // 댓글
//                .antMatchers(HttpMethod.POST, "/api/posts/{postId}/comments").authenticated()
//                .antMatchers(HttpMethod.DELETE, "/api/posts/{postId}/comments/{commentsId}").authenticated()
                // 파일 업로드 권한 설정?
                .anyRequest().permitAll()
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}