# weather-master

This is Yutong Guo's hw 4. 

Please check Swagger (http://localhost:9001/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/search-controller/queryWeatherByCity) 

or PostMan link (https://documenter.getpostman.com/view/20225471/UVyysCJ3) for documentation.


Requirement:
   
      ✅ 1. change gateway setting
      change search service setting and use rest template with spring application name
      from  gateway -> search
                   -> details
      to    gateway -> search -> details
    
      ✅ 2. user can send multiple city names to search service and your response should return different cities' weather
    /search?cities=chi,ashburn,x2,x3
    
      ✅ 5. current project doesn't have any api documents(swagger)

 
* Partially done. Still working on it...
 
      3. you should use multithreading if you need to send multi-requests at same time
          CompletableFuture + WebClient / RestTemplate


       * I use Future in stead of CompletableFuture 


       4. current project doesn't have any log and cannot track request id(uuid)
          add filter in diff services
              get co-relation id from header
              put co-relation id in thread local
              clean up thread local id before give response

      6. current project doesn't have any exception handling

      * no fail tolerance if other services couldn't respond(retry / circuit breaker(hystrix))
      * add security service
      * add configuration service
