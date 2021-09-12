package com.apeiron.abs2.springbootplayground1.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
public class HeaderUpdateFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Enumeration headers = httpServletRequest.getHeaderNames();
        while (headers.hasMoreElements()) {
            log.info("Header Name : {}", headers.nextElement().toString());
        }
        log.info("Accept : {}", httpServletRequest.getHeader("Accept"));
        log.info("User-Agent : {}", httpServletRequest.getHeader("User-Agent"));
        log.info("Connection : {}", httpServletRequest.getHeader("Connection"));
        log.info("correlationId : {}", httpServletRequest.getHeader("correlationId"));
        log.info("Postman-Token : {}", httpServletRequest.getHeader("Postman-Token"));
        log.info("Host : {}", httpServletRequest.getHeader("Host"));
        log.info("Accept-Encoding : {}", httpServletRequest.getHeader("Accept-Encoding"));
        MutableHttpServletRequest mutableHttpServletRequest = new MutableHttpServletRequest(httpServletRequest);
        if (!(httpServletRequest.getHeader("correlationId") != null))
            mutableHttpServletRequest.putHeader("correlationId", "0987654321");
        filterChain.doFilter(mutableHttpServletRequest, httpServletResponse);
    }
}
