package com.byusluer.movierest.model.dto.response;

import com.byusluer.movierest.constants.AppConstants;
import lombok.Data;

@Data
public class JwtAuthResponse {

    private String accessToken;
    private String tokenType;

    public JwtAuthResponse(String accessToken) {
        this.accessToken = accessToken;
        this.tokenType = AppConstants.JWT_TOKEN_NAME.trim();
    }
}
