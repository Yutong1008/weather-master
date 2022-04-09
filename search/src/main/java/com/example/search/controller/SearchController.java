package com.example.search.controller;

import com.example.search.service.SearchService;
//import com.sun.org.apache.xerces.internal.impl.xs.util.ShortListImpl;
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
public class SearchController {

    private final SearchService searchService;
//    @Value("${server.port}")
//    private int randomServerPort;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @Value("${server.port}")
    private int randomServerPort;

    @GetMapping("/weather/search")
    public ResponseEntity<?> getWeatherByCity(@RequestParam(required = true) String city) {
        return new ResponseEntity<>(searchService.getWeatherByCity(city), HttpStatus.OK);
    }
    @GetMapping("/weather/search/{id}")
    public ResponseEntity<?> getWeatherById(@PathVariable int id) {
        return new ResponseEntity<Map>(searchService.getWeatherById(id),HttpStatus.OK);
    }

    @GetMapping("/weather/search/port")
    public ResponseEntity<?> queryWeatherByCity() {
        return new ResponseEntity<>("weather service + " + randomServerPort, HttpStatus.OK);
    }



//        @GetMapping("/details")
//        public ResponseEntity<?> queryWeatherByCity(@RequestParam(required = true) String city) {
//            return new ResponseEntity<>(weatherService.findCityIdByName(city), HttpStatus.OK);
//        }

//
//        @GetMapping("/details/{id}")
//        public ResponseEntity<?> queryWeatherByCity(@PathVariable int id) {
//            return new ResponseEntity<Map>(weatherService.findCityNameById(id), HttpStatus.OK);
//        }
//
//        @GetMapping("/details/port")
//        public ResponseEntity<?> queryWeatherByCity() {
//            return new ResponseEntity<>("weather service + " + randomServerPort, HttpStatus.OK);
//        }
    }


