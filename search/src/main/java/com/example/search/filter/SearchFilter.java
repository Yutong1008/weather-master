package com.example.search.filter;

import ch.qos.logback.core.spi.FilterReply;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


@Configuration
@Order
public class SearchFilter implements Filter{

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("--------------- Initiating the search filter ---------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String correlatedID = request.getHeader(RequestCorrelation.CORRELATION_ID_HEADER);
        if (!currentRequestIsAsyncDispatcher(request)) {
            if (correlatedID == null) {
                correlatedID = UUID.randomUUID().toString();
                LOGGER.info("No correlationId found in Header. Generated : " + correlatedID);
            } else {
                LOGGER.info("Found correlationId in Header : " + correlatedID);
            }

            RequestCorrelation.setId(correlatedID);
        }

        filterChain.doFilter(request, servletResponse);

//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        LOGGER.info("Logging Request  {} : {}", request.getMethod(), request.getRequestURI());

        //call next filter in the filter chain
//        filterChain.doFilter(request, response);

//        LOGGER.info("Logging Response :{}", response.getContentType());
    }
    private boolean currentRequestIsAsyncDispatcher(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getDispatcherType().equals(DispatcherType.ASYNC);
    }

    @Override
    public void destroy() {

    }

}

