package com.movie.lucky.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.lucky.entity.AllMoves;
import com.movie.lucky.entity.MovieRecommendResponse;
import com.movie.lucky.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public AllMoves getAllMovieName() throws JsonProcessingException {
        String url = "http://127.0.0.1:5000/movie-names"; // Replace with your Flask app URL

        // Make the GET call
        String movies =restTemplate.getForObject(url,String.class);

        // object mapper will convert the string into the AllMoves object to convert the string object into well define structure
        ObjectMapper objectMapper = new ObjectMapper();
        AllMoves allMoves = objectMapper.readValue(movies, AllMoves.class);

        return allMoves;
    }

    @Override
    public MovieRecommendResponse getRecommendations(String movie_name) throws IOException {
        String url = "http://127.0.0.1:5000/recommend"; // Replace with your Flask app URL
//
        ObjectMapper objectMapper = new ObjectMapper();

        // Set HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Create HttpEntity with the request body and headers
        HttpEntity<String> entity = new HttpEntity<>(movie_name, headers);

        // Send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        // Extract the body (JSON part) from the response
        String jsonResponse = response.getBody();

        // object mapper will convert the string into the MovieRecommendResponse object to convert the string object into well define structure
        MovieRecommendResponse recommend = objectMapper.readValue(jsonResponse, MovieRecommendResponse.class);

        return recommend;
    }

    @Override
    public String getHome() {
        String url = "http://127.0.0.1:5000/";
        String home = restTemplate.getForObject(url,String.class);
        return home;
    }
}
