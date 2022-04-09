package com.example.search.service;


import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public interface SearchService {
    List<Integer> getWeatherByCity(String city);
    Map<String, Map> getWeatherById(int id);
    List<Map> getWeather(String cities);
//    private final RestTemplate restTemplate;
//    @Autowired
//    public SearchService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    //    @Autowired
//    public SearchService(RestTemplate restTemplate){
//        this.restTemplate = restTemplate;
//    }
//    public List<Weather> getWeatherByCity(String city) {
//        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setAccept(singletonList(MediaType.APPLICATION_JSON));
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
//        return restTemplate.exchange("http://localhost/search-service/weather", HttpMethod.GET, entity, SearchResponse.class).getBody().getData();
//    }


}

