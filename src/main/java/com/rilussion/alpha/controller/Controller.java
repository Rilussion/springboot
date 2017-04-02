package com.rilussion.alpha.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nkakarla on 4/2/17.
 */

@RestController
@RequestMapping("/tickers")
public class Controller {

    @RequestMapping(value = "/{ticker}", method = RequestMethod.GET)
    public String getTicker(@PathVariable String ticker){

        return "Alphabet";
    }
}
