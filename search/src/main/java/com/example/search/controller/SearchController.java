package com.example.search.controller;

import com.example.search.service.SearchService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@Slf4j
public class SearchController {

    private final SearchService searchService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @Value("${server.port}")
    private int port;

    @GetMapping("/weather/search")
    @Operation(summary ="Get weather", responses = {
            @ApiResponse(description = "Get weather success", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(description = "Weather not found", responseCode = "409",content = @Content)
    })
    public ResponseEntity<?> getIdByCities(@ApiParam(value ="city",required = true) @RequestParam(required = true) String city) {
        logger.info("Get the weather details of the input cities");
//        List<Integer> list = restTemplate.getForObject("http://weather-details-service/details?city=" + city, List.class);
        return new ResponseEntity<>(searchService.getWeather(city),HttpStatus.OK);
//        return ResponseEntity<>(searchService.getIdByCity(city).thenApply(ResponseEntity::ok);

    }



    @GetMapping("/weather/search/{id}")
    @Operation(summary ="Get weather", responses = {
            @ApiResponse(description = "Get city's id success", responseCode = "200",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(description = "City's id not found", responseCode = "409",content = @Content)
    })
    public ResponseEntity<?> getWeatherById(@ApiParam(value = "id", required = true) @PathVariable int id) {
//        Map<String, Map> weatherMap = restTemplate.getForObject("http://weather-details-service/details/" + id, Map.class);
        return new ResponseEntity<Map>(searchService.getWeatherById(id), HttpStatus.OK);
    }
    //    public CompletableFuture<ResponseEntity<?>> getWeatherById(@ApiParam(value = "id", required = true) @PathVariable int id) {
////        List<CompletableFuture> futureList = new ArrayList<>();
////        for (CompletableFuture cf : futureList) {
////            futureList.add(CompletableFuture.supplyAsync(()-> searchService.getWeatherById(id)));
////        }
////        return CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]))
////                .thenApply(Void ->
////                futureList.stream()
////                        .map(CompletableFuture::join)
////                        .collect(Collectors.toList()));
//////		        .thenApply(x -> x)
//////                .OrTimeOut(5, TimeUnit.SECOND)
//////                .join();
//        return searchService.getWeatherById(id).thenApply(ResponseEntity :: ok);
//    }

        @GetMapping("/weather/search/port")
        public ResponseEntity<?> WeatherByCity () {
            return new ResponseEntity<>("weather service + " + port, HttpStatus.OK);
        }


    }


