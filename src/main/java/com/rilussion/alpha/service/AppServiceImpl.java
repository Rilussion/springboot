package com.rilussion.alpha.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rilussion.alpha.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by nkakarla on 4/28/17.
 */

@Service
public class AppServiceImpl {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> getGreeting(String city) throws IOException {
        ResponseEntity<String> response = null;

        HttpStatus responseStatusCode = HttpStatus.OK;
        try{
            response = restTemplate.getForEntity(appConfig.getWeatherApiUri() + "/data/2.5/weather?q=" + city + "&APPID=bca697c818b36a43cc32758f09361b3a", String.class);
        }
        catch (HttpStatusCodeException ex){
            responseStatusCode = ex.getStatusCode();
        }

        if(responseStatusCode.is4xxClientError()) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("you fd up");
        }

        if(responseStatusCode.is5xxServerError()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("I fd up");
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response.getBody());
        Double temp = node.get("main").get("temp").asDouble();
        Double dayTime = (node.get("sys").get("sunset").asDouble() - node.get("sys").get("sunrise").asDouble()) / 3600;

        return ResponseEntity.status(HttpStatus.OK).body(
                String.format("The temperature in %s is %.2f F. Daylight duration: %.2f hours", city, (temp * 9 / 5) - 459.67, dayTime));

    }
}