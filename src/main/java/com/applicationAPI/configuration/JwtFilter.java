package com.applicationAPI.configuration;

import com.applicationAPI.controller.Form.LoginForm;
import com.applicationAPI.execption.BusinessLogicException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;

public class JwtFilter extends GenericFilterBean {

    private Authentication authentication;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String URL = request.getRequestURI();
        if (URL.endsWith("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    System.out.println("Header: " + request.getHeader(headerNames.nextElement()));
                }
            }
            authentication = this.validateToken(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public Authentication validateToken(HttpServletRequest request) {
        LoginForm user = null;
        try {
            String token = request.getHeader("Authorization");
            if (token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
                System.out.println(token);
                user = JwtUtil.getToken(token);
            }
        } catch (Exception ex) {
            throw new BusinessLogicException(" Error validando token session.");
        } finally {
            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        }

    }
}
