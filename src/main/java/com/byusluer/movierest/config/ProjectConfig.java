package com.byusluer.movierest.config;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {

    @Value("${tmdb.api.key}")
    private String apiKey;
    @Value("${tmdb.api.url}")
    private String apiUrl;


    private final AWSSimpleSystemsManagement awsManagement;

    private final AWSKMS awskms;

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }


    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            System.out.println("REQUEST:");
            System.out.println(requestTemplate.method() + " " + requestTemplate.url());
            requestTemplate.headers().forEach((name, values) -> values.forEach(value -> System.out.println(name + ": " + value)));
            System.out.println(requestTemplate.requestBody().asString());
            System.out.print("api key ");
            printAPiKey();
        };
    }


   void printAPiKey(){
       GetParameterRequest request = new GetParameterRequest();
       request.withName("api_key").setWithDecryption(Boolean.TRUE);
       GetParameterResult parameterResult = awsManagement.getParameter(request);
       System.out.println(parameterResult.getParameter().getValue());






   }
}




