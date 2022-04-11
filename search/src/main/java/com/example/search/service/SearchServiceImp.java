package com.example.search.service;

import com.example.search.dto.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
@Service
public class SearchServiceImp implements SearchService{
    private final RestTemplate restTemplate;
    public static final String queryWeatherByCity = "https://www.metaweather.com/api/location/search/?query=";
    public static final String queryWeatherById = "https://www.metaweather.com/api/location/";
    @Autowired
    public SearchServiceImp (RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    @Retryable(include = IllegalAccessError.class)
    public List<Integer> getWeatherByCity(String city) {
//        Map<String, String> cityMap = Collections.singletonMap("Malibu","Santa Monica");
            City[] cities = restTemplate.getForObject(queryWeatherByCity + city, City[].class);
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
        Map<String, Map> weatherMap = restTemplate.getForObject(queryWeatherById + id, HashMap.class);
        return weatherMap;
    }

    public List<Map> getWeather(String cities) {
        List<Map> weatherList = new ArrayList<>();
        String[] cityArray = cities.split(",");
        List<City[]> cityList = new ArrayList<>();
        for (String s : cityArray) {
            City[] city = restTemplate.getForObject(queryWeatherByCity + s, City[].class);
            cityList.add(city);
        }
        List<List<Integer>> citiesList = new ArrayList<>();
        for (City[] city : cityList) {
            List<Integer> cityId = new ArrayList<>();
                for (City c : city) {
                    if (c != null && c.getWoeid() != null) {
                        cityId.add(c.getWoeid());
                    }
                }
                citiesList.add(cityId);
        }
        for (List<Integer> cityId : citiesList) {
            for (int id : cityId) {
                weatherList.add(getWeatherById(id));
            }
        }
        return weatherList;
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