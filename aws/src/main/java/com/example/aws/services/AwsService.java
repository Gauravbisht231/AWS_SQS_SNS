//package com.example.aws.model;
//
//import com.example.aws.dto.PubMessageDto;
//import com.example.aws.dto.SubsEmail;
//import com.example.aws.dto.SubsSqs;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import software.amazon.awssdk.services.sns.SnsClient;
//import software.amazon.awssdk.services.sns.model.*;
//import software.amazon.awssdk.services.sqs.SqsClient;
//import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
//import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
//import software.amazon.awssdk.services.sqs.model.SqsException;
//
//@Service
//@RequiredArgsConstructor
//public class AwsService {
//    private final SnsClient snsClient;
//    private final SqsClient sqsClient;
//
//    @Value("${aws.sns.topic-arn}")
//    private String topicArn;
//    @Value("${aws.sqs.queue-arn}")
//    private String queueArn;
//
//
//    //create sns topic below
//    public String createSnsTopic(String topicName){
//        CreateTopicResponse result;
//        try {
//            CreateTopicRequest request = CreateTopicRequest.builder()
//                    .name(topicName)
//                    .build();
//
//            result = snsClient.createTopic(request);
//            return result.topicArn();
////            CreateTopicResponse createTopicResponse = snsClient.createTopic(request);
////         return  createTopicResponse.topicArn();
//
//        } catch (SnsException e) {
//            System.err.println(e.awsErrorDetails().errorMessage());
//            System.exit(1);
//        }
//        return "";
//    }
//    // crate sqs Q
//    public String createQueue(String queueName) {
//        try {
//            CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
//                    .queueName(queueName)
//                    .build();
//
//            CreateQueueResponse createQueueResponse = sqsClient.createQueue(createQueueRequest);
//            return createQueueResponse.queueUrl();
//        } catch (SqsException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to create SQS queue", e);
//        }
//    }
//
//    // publish message
//
//    public void pubTopic(PubMessageDto pubMessageDto) {
//        try {
//            System.out.println(topicArn + pubMessageDto.getTopicName());
//            PublishRequest request = PublishRequest.builder()
//                    .message(pubMessageDto.getMessage())
//                    .topicArn(topicArn + pubMessageDto.getTopicName())
//                    .build();
//
//
//            PublishResponse result = snsClient.publish(request);
//            System.out
//                    .println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());
//
//        } catch (SnsException e) {
//            System.err.println(e.awsErrorDetails().errorMessage());
//            System.exit(1);
//        }
//    }
//
//    // subscribe the sqs to sns
//public void subscribeSqs(SubsSqs subsSqs){
//  try {
//        SubscribeRequest subscribeRequest = SubscribeRequest.builder()
//                .protocol("sqs")
//                .endpoint(queueArn + subsSqs.getQueueName())
//                .returnSubscriptionArn(true)
//                .topicArn(topicArn + subsSqs.getTopicName())
//                .build();
//
//
//        SubscribeResponse subscribeResponse = snsClient.subscribe(subscribeRequest);
//        System.out.println("Subscription ARN: " + subscribeResponse.subscriptionArn());
//    }catch (SnsException | SqsException e){
//    e.printStackTrace();
//  }
//}
//
////subscribing email to sns topic
//    public String subEmail(SubsEmail subsEmail) {
//        try {
//            SubscribeRequest request = SubscribeRequest.builder()
//                    .protocol("email")
//                    .endpoint(subsEmail.getEmail())
//                    .returnSubscriptionArn(true)
//                    .topicArn(topicArn + subsEmail.getTopicName())
//                    .build();
//
//            SubscribeResponse result = snsClient.subscribe(request);
//            System.out.println("Subscription ARN: " + result.subscriptionArn() + "\n\n Status is "
//                    + result.sdkHttpResponse().statusCode());
//            return "Subscribed email successfully" + subsEmail.getEmail();
//
//        } catch (SnsException e) {
//            System.err.println(e.awsErrorDetails().errorMessage());
//            System.exit(1);
//            return "failed to send mail" + e.getMessage();
//        }
//    }
//
//
//}
