package com.rilussion.alpha.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * Created by nkakarla on 4/28/17.
 */

@Component
@ConfigurationProperties
public class AppConfig {

    public URI getWeatherApiUri() {
        setWeatherApiUri();
        return weatherApiUri;
    }

    public void setWeatherApiUri() {
        this.weatherApiUri = URI.create(this.getWeatherApiHost() + ":" + this.getWeatherApiPort().toString());
    }

    public String getWeatherApiHost() {
        return weatherApiHost;
    }

    public void setWeatherApiHost(String weatherApiHost) {
        this.weatherApiHost = weatherApiHost;
    }

    public Integer getWeatherApiPort() {
        return weatherApiPort;
    }

    public void setWeatherApiPort(Integer weatherApiPort) {
        this.weatherApiPort = weatherApiPort;
    }

    URI weatherApiUri;
    String weatherApiHost;
    Integer weatherApiPort;
}