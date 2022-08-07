package com.webflux;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RequiredArgsConstructor
public class MyFilter2 implements Filter {

    private final EventNotify eventNotify;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("************************* filter2 request *************************");
        eventNotify.add("add event");
        eventNotify.setChange(true);

    }
}
