package com.example.aws.services.snsService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sqs.SqsClient;

@Service
@RequiredArgsConstructor
public class CreateSns {
    private final SnsClient snsClient;
    private final SqsClient sqsClient;

    @Value("${aws.sns.topic-arn}")
    private String topicArn;
    @Value("${aws.sqs.queue-arn}")
    private String queueArn;

    public String createSnsTopic(String topicName){
        CreateTopicResponse result;
        try {
            CreateTopicRequest request = CreateTopicRequest.builder()
                    .name(topicName)
                    .build();

            result = snsClient.createTopic(request);
            return result.topicArn();
//            CreateTopicResponse createTopicResponse = snsClient.createTopic(request);
//         return  createTopicResponse.topicArn();

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return "";
    }
}
