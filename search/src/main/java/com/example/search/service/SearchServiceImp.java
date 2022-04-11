package com.example.search.service;

import com.example.search.dto.City;
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
    public List<Integer> getWeatherByCity(String city) {
//        Map<String, String> cityMap = Collections.singletonMap("Malibu","Santa Monica");
            City[] cities = restTemplate.getForObject("https://www.metaweather.com/api/location/search/?query= " + city, City[].class);
            List<Integer> ans = new ArrayList<>();
        assert cities != null;
        for (City c : cities) {
                if (c != null && c.getWoeid() != null) {
                    ans.add(c.getWoeid());
                }
            }
            return ans;
    }
    @Override
    public Map<String, Map> getWeatherById(int id) {
        Map<String, Map> weatherMap = restTemplate.getForObject("https://www.metaweather.com/api/location/" + id, HashMap.class);
        return weatherMap;
    }
    @Override
    //String city = "chicago,santa monica, new york"
    public List<Map> getWeather(String cities) {
            List<Map> weatherList = new ArrayList<>();
            String[] cityArray = cities.split(",");
            //            List<String> cities = new ArrayList<>();
            //            cities.addAll(Arrays.asList(cityArray));
            List<City[]> cityList = new ArrayList<>();
        for (String s : cityArray) {
            City[] city = restTemplate.getForObject("https://www.metaweather.com/api/location/search/?query= " + s, City[].class);
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
//            for (City[] key : cityList) {
//                List<Integer> cityId = new ArrayList<>();
//                for (City c : key) {
//                    if (c != null && c.getWoeid() != null) {
//                        cityId.add(c.getWoeid());
//                    }
//                }
//                citiesList.add(cityId);
//            }
        for (List<Integer> cityId : citiesList) {
            for (int id : cityId) {
                weatherList.add(getWeatherById(id));
            }
        }
        return weatherList;
    }
}
/*
* idea:
* 1. collect all the cities and split to a list of city[]
* 2. for loop iterate all the cities get ids of cities
* 3.
* */