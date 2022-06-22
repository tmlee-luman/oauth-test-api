package com.lumanlab.authtestserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lumanlab.authtestserver.infrastructure.jwt.JwtTokenHelper;
import com.lumanlab.authtestserver.security.entrypoint.JwtAuthenticationEntryPoint;
import com.lumanlab.authtestserver.security.filter.JwtAuthenticationFilter;
import com.lumanlab.authtestserver.security.url.BasicAuthenticationExclusionV1Uri;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final JwtTokenHelper jwtTokenHelper;

    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .httpBasic()
                .disable()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, BasicAuthenticationExclusionV1Uri.getUris())
                .permitAll()
                .antMatchers("/test-db-console/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(userDetailsService, jwtTokenHelper), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint(objectMapper));


        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOrigin("*");

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }

}
