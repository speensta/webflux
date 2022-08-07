package com.webflux;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@RequiredArgsConstructor
public class MyFilter implements Filter {

    private final EventNotify eventNotify;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("************************* filter request *************************");

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setContentType("text/event-stream; charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();

        for(int i=0;i<5;i++) {
            out.println("응답 "+i);
            out.flush();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        while(true) {
            try {
                if(eventNotify.isChange()) {
                    out.println("응답:"+eventNotify.getEvents().get(eventNotify.getEvents().size()-1));
                    out.flush();
                    eventNotify.setChange(false);
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
