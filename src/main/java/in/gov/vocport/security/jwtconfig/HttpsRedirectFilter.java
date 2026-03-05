package in.gov.vocport.security.jwtconfig;//package com.dplocisif.DPLOCISIF.config.jwtconfig;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class HttpsRedirectFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpReq = (HttpServletRequest) request;
//        HttpServletResponse httpResp = (HttpServletResponse) response;
//
//        if (httpReq.getScheme().equals("http")) {
//            String httpsUrl = "https://" + httpReq.getServerName()
//                    + ":8443"
//                    + httpReq.getRequestURI()
//                    + (httpReq.getQueryString() != null ? "?" + httpReq.getQueryString() : "");
//
//            httpResp.sendRedirect(httpsUrl);
//            return; // Don't continue the chain, redirect done
//        }
//
//        chain.doFilter(request, response); // Proceed if already HTTPS
//    }
//}
