package com.example.aws.services.emailService;

import com.example.aws.dto.SubsEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;
import software.amazon.awssdk.services.sqs.SqsClient;
@Service
@RequiredArgsConstructor

public class SubscribeEmail {
    private final SnsClient snsClient;
    private final SqsClient sqsClient;

    @Value("${aws.sns.topic-arn}")
    private String topicArn;
    @Value("${aws.sqs.queue-arn}")
    private String queueArn;
    public String subEmail(SubsEmail subsEmail) {
        try {
            SubscribeRequest request = SubscribeRequest.builder()
                    .protocol("email")
                    .endpoint(subsEmail.getEmail())
                    .returnSubscriptionArn(true)
                    .topicArn(topicArn + subsEmail.getTopicName())
                    .build();

            SubscribeResponse result = snsClient.subscribe(request);
            System.out.println("Subscription ARN: " + result.subscriptionArn() + "\n\n Status is "
                    + result.sdkHttpResponse().statusCode());
            return "Subscribed email successfully" + subsEmail.getEmail();

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
            return "failed to send mail" + e.getMessage();
        }
    }
}
