package com.movie.lucky.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.movie.lucky.entity.AllMoves;
import com.movie.lucky.entity.MovieRecommendResponse;

import java.io.IOException;
import java.util.List;

public interface RecommendService {
    public AllMoves getAllMovieName() throws JsonProcessingException;
    public MovieRecommendResponse getRecommendations(String movieName) throws IOException;

    public String getHome();
}
