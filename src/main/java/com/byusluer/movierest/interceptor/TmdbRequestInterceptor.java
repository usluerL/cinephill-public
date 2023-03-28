package com.byusluer.movierest.interceptor;

import com.byusluer.movierest.service.aws.AwsSSMService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TmdbRequestInterceptor implements RequestInterceptor {

    @Value("${tmdb.api.key}")
    private String apiKey;


    private final AwsSSMService awsSSMService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query("api_key", awsSSMService.getParam("api_key"));
    }
}
