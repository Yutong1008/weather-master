package com.example.search.client;

import com.example.search.filter.RequestCorrelation;
import com.example.search.filter.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CorrelationClient implements RestClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchFilter.class);
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getString(String uri) {
        String correlationId = RequestCorrelation.getId();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(RequestCorrelation.CORRELATION_ID_HEADER, correlationId);

        LOGGER.info("start REST request to {} with correlationId {}", uri, correlationId);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<String>(httpHeaders), String.class);

        LOGGER.info("completed REST request to {} with correlationId {}", uri, correlationId);

        return response.getBody();
    }
}

