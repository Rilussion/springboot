package com.rilussion.alpha.controller;

import com.rilussion.alpha.service.AppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by nkakarla on 4/2/17.
 */

@RestController
@RequestMapping("/tickers")
public class Controller {

    @Autowired
    private AppServiceImpl appServiceImpl;

    @RequestMapping(value = "/ticker/{ticker}", method = RequestMethod.GET)
    public String getTicker(@PathVariable String ticker){

        return "Alphabet";
    }


    @RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
    public ResponseEntity<String> getGreeting(@PathVariable String city) throws IOException {

      return appServiceImpl.getGreeting(city);
    }
}
