package com.example.aws.services.snsService;

import com.example.aws.dto.PubMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sqs.SqsClient;
@Service
@RequiredArgsConstructor

public class PublishMessage {
    private final SnsClient snsClient;
    private final SqsClient sqsClient;

    @Value("${aws.sns.topic-arn}")
    private String topicArn;
    @Value("${aws.sqs.queue-arn}")
    private String queueArn;

    public void pubTopic(PubMessageDto pubMessageDto) {
        try {
            System.out.println(topicArn + pubMessageDto.getTopicName());
            PublishRequest request = PublishRequest.builder()
                    .message(pubMessageDto.getMessage())
                    .topicArn(topicArn + pubMessageDto.getTopicName())
                    .build();


            PublishResponse result = snsClient.publish(request);
            System.out
                    .println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

}
