package com.movie.lucky.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.lucky.entity.AllMoves;
import com.movie.lucky.entity.MovieRecommendResponse;
import com.movie.lucky.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    @GetMapping(value = "/allMovies", produces = "application/json")
    public AllMoves getAllMovies() throws JsonProcessingException {
        return recommendService.getAllMovieName();
    }

    @PostMapping(value = "/recommend", produces = "application/json")
    public MovieRecommendResponse recommendMovies(@RequestBody String movie_name) throws IOException {
        return recommendService.getRecommendations(movie_name);
    }

    @GetMapping(value = "/home", produces = "application/json")
    public String getHome() throws JsonProcessingException {

        String testJson = """
    {
        "name": ["Avatar", "Pirates of the Caribbean: At World's End", "Spectre"]
    }
""";
        ObjectMapper objectMapper = new ObjectMapper();
        AllMoves testAllMoves = objectMapper.readValue(testJson, AllMoves.class);
        System.out.println("Test object: " + testAllMoves.getName().size());
        System.out.println("Test object: " + testAllMoves.getName().toString());
        return recommendService.getHome();
    }
}
