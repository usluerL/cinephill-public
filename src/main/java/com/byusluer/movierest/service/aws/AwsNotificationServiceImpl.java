package com.byusluer.movierest.service.aws;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.services.sns.AmazonSNS;
import com.byusluer.movierest.entity.UserProfile;
import com.byusluer.movierest.model.aws.TVSHOWMailTemplate;
import com.byusluer.movierest.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AwsNotificationServiceImpl implements NotificationService {

    private final AmazonSNS amazonSNS;
    private String topicArn;
    private final AmazonSimpleEmailService amazonSES;
    private final TVSHOWMailTemplate template;

    @Override
    public void sendNotification(UserProfile userProfile, String message) {

        try {
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(template.getRecipientEmail()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(template.getHtmlBody()))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(template.getBody())))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(message)))
                    .withSource(template.getSenderEmail());


            amazonSES.sendEmail(request);
            System.out.println("email sent");
        } catch (Exception e) {
            System.out.println("The email was not sent. Error message: " + e.getMessage());
            e.printStackTrace();
        }


    }


}
