package com.example.search.service;


import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public interface SearchService {
    List<Integer> getWeatherByCity(String city);
    Map<String, Map> getWeatherById(int id);
    List<Map> getWeather(String cities);


}

