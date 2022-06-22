package com.lumanlab.authtestserver.security.filter;


import com.lumanlab.authtestserver.infrastructure.jwt.JwtTokenHelper;
import com.lumanlab.authtestserver.security.dto.AuthenticationMember;
import com.lumanlab.authtestserver.security.exception.InvalidRequestTokenException;
import com.lumanlab.authtestserver.security.url.BasicAuthenticationExclusionV1Uri;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String AUTHENTICATION_HEADER_PREFIX = "Bearer ";

    private final UserDetailsService userDetailsService;

    private final JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (haveTokenInHeader(authorizationHeader) && !BasicAuthenticationExclusionV1Uri.isExclusionUri(request.getRequestURI())) {
            String email = jwtTokenHelper.parseToken(extractAccessToken(authorizationHeader));

            setAuthenticationInSecurityContextHolder(
                    (AuthenticationMember) userDetailsService.loadUserByUsername(email));
        }

        filterChain.doFilter(request, response);
    }

    private boolean haveTokenInHeader(String accessToken) {
        return StringUtils.hasText(accessToken);
    }

    private String extractAccessToken(String authorizationHeader) {
        if (Objects.isNull(authorizationHeader) || !authorizationHeader.contains(AUTHENTICATION_HEADER_PREFIX)) {
            throw new InvalidRequestTokenException();
        }
        return authorizationHeader.substring(AUTHENTICATION_HEADER_PREFIX.length());
    }

    private void setAuthenticationInSecurityContextHolder(AuthenticationMember authenticationMember) {
        SecurityContextHolder.getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                authenticationMember,
                                null,
                                authenticationMember.getAuthorities())
                );
    }
}
