package com.example.search.controller;

import com.example.search.Futures.SearchCallable;
import com.example.search.service.SearchService;
//import com.sun.org.apache.xerces.internal.impl.xs.util.ShortListImpl;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFutureAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class SearchController {

    private final SearchService searchService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    private ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));


    @Value("${server.port}")
    private int randomServerPort;

    @GetMapping("/weather/search")
//    @ApiOperation(value = "Search By Id")
    public ResponseEntity<?> getWeatherByCity(@ApiParam(value ="city",required = true) @RequestParam(required = true) String city) {
        logger.info("Get the weather details of the input city");
        return new ResponseEntity<>(searchService.getWeather(city), HttpStatus.OK);
    }

    @GetMapping("/weather/search/{id}")
//    @ApiOperation(value = "Search By Id")
    public ResponseEntity<?> getWeatherById(@ApiParam(value = "id", required = true) @PathVariable int id) {
        return new ResponseEntity<Map>(searchService.getWeatherById(id), HttpStatus.OK);
    }
    @GetMapping("/weather/search/port")
    public ResponseEntity<?> queryWeatherByCity() {
        return new ResponseEntity<>("weather service + " + randomServerPort, HttpStatus.OK);
    }
//    @RequestMapping("weather/search")
//    public DeferredResult<String> getWeathers() {
//        return new ListenableFutureAdapter<>(service.submit(new SearchCallable<>(searchService::getWeather)));
//    }
}


