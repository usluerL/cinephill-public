package com.byusluer.movierest.service.aws;


import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwsSSMService {

    private final AWSSimpleSystemsManagement awsSimpleSystemsManagement;


    public String getParam(String name) {

        GetParameterRequest request = new GetParameterRequest();
        request.withName(name).setWithDecryption(Boolean.TRUE);
        GetParameterResult parameterResult = awsSimpleSystemsManagement.getParameter(request);
        return parameterResult.getParameter().getValue();
    }

}
