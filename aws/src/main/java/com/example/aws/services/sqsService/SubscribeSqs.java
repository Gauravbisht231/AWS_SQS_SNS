package com.example.aws.services.sqsService;

import com.example.aws.dto.SubsSqs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SqsException;
@Service
@RequiredArgsConstructor

public class SubscribeSqs {
    private final SnsClient snsClient;
    private final SqsClient sqsClient;

    @Value("${aws.sns.topic-arn}")
    private String topicArn;
    @Value("${aws.sqs.queue-arn}")
    private String queueArn;
    public void subscribeSqs(SubsSqs subsSqs){
        try {
            SubscribeRequest subscribeRequest = SubscribeRequest.builder()
                    .protocol("sqs")
                    .endpoint(queueArn + subsSqs.getQueueName())
                    .returnSubscriptionArn(true)
                    .topicArn(topicArn + subsSqs.getTopicName())
                    .build();


            SubscribeResponse subscribeResponse = snsClient.subscribe(subscribeRequest);
            System.out.println("Subscription ARN: " + subscribeResponse.subscriptionArn());
        }catch (SnsException | SqsException e){
            e.printStackTrace();
        }
    }
}
