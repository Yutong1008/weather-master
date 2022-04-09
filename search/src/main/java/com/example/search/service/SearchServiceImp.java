package com.example.search.service;

import com.example.search.dto.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchServiceImp implements SearchService{
    private final RestTemplate restTemplate;
    @Autowired
    public SearchServiceImp (RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    @Retryable(include = IllegalAccessError.class)
    public List<Integer> getWeatherByCity(String city) {
//        Map<String, String> cityMap = Collections.singletonMap("Malibu","Santa Monica");
            City[] cities = restTemplate.getForObject("https://www.metaweather.com/api/location/search/?query= " + city, City[].class);
            List<Integer> ans = new ArrayList<>();
            for (City c : cities) {
                if (c != null && c.getWoeid() != null) {
                    ans.add(c.getWoeid());
                }
            }
            return ans;
    }

    @Override
    public Map<String, Map> getWeatherById(int id) {
        Map<String,Map> weatherMap = restTemplate.getForObject("https://www.metaweather.com/api/location/"+ id, HashMap.class);
        return weatherMap;
    }

    @Override
    public List<Map> getWeather(String cities) {
        String[] cityArray = cities.split(",");
//            List<String> cities = new ArrayList<>();
//            cities.addAll(Arrays.asList(cityArray));
        for (String s: cityArray){

        }
        List<List<Integer>> citiesList = new ArrayList<>();
        List<Map<String,Map>>

    }

}
