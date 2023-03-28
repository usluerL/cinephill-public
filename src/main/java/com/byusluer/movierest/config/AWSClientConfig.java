package com.byusluer.movierest.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AWSClientConfig {

    private final AwsConfig awsConfig;

    @Bean
    public AmazonSNS amazonSNS() {
        AWSCredentials credentials = new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey());
        return AmazonSNSClientBuilder.standard()
                .withRegion(awsConfig.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        AWSCredentials credentials = new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey());
        return AmazonSimpleEmailServiceClientBuilder.standard().withRegion(awsConfig.getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }

    @Bean
    AWSSimpleSystemsManagement awsSimpleSystemsManagement() {
        AWSCredentials credentials = new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey());
        return AWSSimpleSystemsManagementClientBuilder.standard().withRegion(awsConfig.getRegion()).withCredentials(
                new AWSStaticCredentialsProvider(credentials)).build();
    }

    @Bean
    AWSKMS awskms(){
        AWSCredentials credentials = new BasicAWSCredentials(awsConfig.getAccessKey(), awsConfig.getSecretKey());
        return AWSKMSClientBuilder.standard().withRegion(awsConfig.getRegion()).withCredentials(
                new AWSStaticCredentialsProvider(credentials)).build();
    }
}
