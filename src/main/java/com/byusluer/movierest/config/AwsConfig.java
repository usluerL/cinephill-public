package com.byusluer.movierest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "aws", ignoreUnknownFields = false)
@Data
public class AwsConfig {

    private String accessKey;
    private String secretKey;
    private String region;
    private String topicArn;

}
