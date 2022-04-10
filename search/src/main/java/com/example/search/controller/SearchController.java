package com.example.search.controller;

import com.example.search.service.SearchService;
//import com.sun.org.apache.xerces.internal.impl.xs.util.ShortListImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@Api
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @Value("${server.port}")
    private int randomServerPort;

    @GetMapping("/weather/search")
//    @ApiOperation(value = "Search By Id")
    public ResponseEntity<?> getWeatherByCity(@ApiParam(value ="city",required = true) @RequestParam(required = true) String city) {
        return new ResponseEntity<>(searchService.getWeather(city), HttpStatus.OK);
    }

    //    @GetMapping("/weather/search")
//    public ResponseEntity<?> getWeatherByCities(@RequestParam(required = true) String cities) {
//        return new ResponseEntity<>(searchService.getWeather(cities), HttpStatus.OK);
//    }
//    @ApiOperation(value = "Search By Id")
    @GetMapping("/weather/search/{id}")
//    @ApiOperation(value = "Search By Id")
    public ResponseEntity<?> getWeatherById(@ApiParam(value = "id", required = true) @PathVariable int id) {
        return new ResponseEntity<Map>(searchService.getWeatherById(id), HttpStatus.OK);
    }
    @GetMapping("/weather/search/port")
    public ResponseEntity<?> queryWeatherByCity() {
        return new ResponseEntity<>("weather service + " + randomServerPort, HttpStatus.OK);
    }
}


