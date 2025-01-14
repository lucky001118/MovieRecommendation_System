package com.movie.lucky.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponce {
    private String jwt;
    private String message;
    private boolean status;
}
