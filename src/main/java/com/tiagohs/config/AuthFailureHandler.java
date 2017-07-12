package com.tiagohs.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        redirectStrategy.sendRedirect(request, response, "/login?msg=Bad Credentials");
    }
}
