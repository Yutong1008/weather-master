package com.example.search.service;

import com.example.search.exception.CityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class SearchServiceImp implements SearchService{
    private final RestTemplate restTemplate;
    @Autowired
    public SearchServiceImp (RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    @Retryable(include = IllegalAccessError.class)
    public List<Integer> getIdByCity(String city) {
        List<Integer> list = restTemplate.getForObject("http://weather-details-service/details?city=" + city, List.class);
            return list;
    }

    @Override
    public Map<String, Map> getWeatherById(int id) {
        Map<String, Map> weatherMap = restTemplate.getForObject("http://weather-details-service/details/" + id, Map.class);
        return weatherMap;
    }
    @Override
    public List<Map> getWeather(String cities) {
        try {
            List<Map> weatherList = new ArrayList<>();
            String[] cityArray = cities.split(",");
            List<List<Integer>> cityList = new ArrayList<>();
            for (String s : cityArray) {
                List<Integer> list = restTemplate.getForObject("http://weather-details-service/details?city=" + s, List.class);
                cityList.add(list);
            }
            for (List<Integer> cityId : cityList) {
                for (int id : cityId) {
                    weatherList.add(getWeatherById(id));
                }
            }
            return weatherList;
        } catch(Exception e) {
            throw new CityNotFound("Not found this city");
        }
    }
//    @Override
//    public List<Map> getWeather(String cities) {
//        List<Map> weatherList = new ArrayList<>();
//        List<Integer> cityList = new ArrayList<>();
//        City[] city = restTemplate.getForObject( queryWeatherByCity + cities, City[].class);
//        for (City c : city) {
//            if (c != null && c.getWoeid() != null) {
//                cityList.add(c.getWoeid());
//            }
//        }
//        for (int id : cityList) {
//
//            weatherList.add(getWeatherById(id));
//        }
//
//        return weatherList;
//    }
}
/*
* idea:
* 1. collect all the cities and split to a list of city[]
* 2. for loop iterate all the cities get ids of cities
* 3.
* */