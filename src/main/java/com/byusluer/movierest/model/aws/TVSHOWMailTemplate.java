package com.byusluer.movierest.model.aws;

import com.byusluer.movierest.constants.AppConstants;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TVSHOWMailTemplate {

    private String subject;
    private String recipientEmail;
    private String senderEmail;
    private String body;
    private String  textBody;
    private String  htmlBody;
    static final String HTML_BODY = "<h1>Amazon SES test (AWS SDK for Java)</h1>"
            + "<p>This email was sent with <a href='https://aws.amazon.com/ses/'>"
            + "Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>"
            + "AWS SDK for Java</a>";
    static final String TEXT_BODY = "This email was sent through Amazon SES "
            + "using the the AWS SDK for Java.";


    public TVSHOWMailTemplate() {
        this.subject = "New TV Show Released";
        this.recipientEmail = AppConstants.MY_MAIL;
        this.senderEmail = AppConstants.MY_MAIL;
        this.body = TEXT_BODY;
        this.htmlBody = HTML_BODY;

    }
}
