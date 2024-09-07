package com.example.aws.services.sqsService;

import com.example.aws.dto.CreateDLQ;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CreateSqs {
    private final SnsClient snsClient;
    private final SqsClient sqsClient;
    @Value("${aws.sns.topic-arn}")
    private String topicArn;
    @Value("${aws.sqs.queue-arn}")
    private String queueArn;
    @Value("${aws.sqs.queue-url}")
    private String queueUrl;
    public String createQueue(String queueName) {
        try {
            CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
                    .queueName(queueName)
                    .build();

            CreateQueueResponse createQueueResponse = sqsClient.createQueue(createQueueRequest);

            return createQueueResponse.queueUrl();
        } catch (SqsException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create SQS queue", e);
        }
    }
public void CreateDLQ(CreateDLQ createDLQ){
    CreateQueueRequest createQueueRequest = CreateQueueRequest.builder()
            .queueName(createDLQ.getChildQ())
            .build();

    CreateQueueResponse createQueueResponse = sqsClient.createQueue(createQueueRequest);
    String qUrl = createQueueResponse.queueUrl();

    // Get the DLQ ARN
    GetQueueAttributesRequest getDLQAttributesRequest =  GetQueueAttributesRequest.builder()
            .queueUrl(qUrl)
            .attributeNamesWithStrings(QueueAttributeName.QUEUE_ARN.toString()).build();
    GetQueueAttributesResponse getDLQAttributesResult = sqsClient.getQueueAttributes(getDLQAttributesRequest);
    String dlqArn = getDLQAttributesResult.attributes().get(QueueAttributeName.QUEUE_ARN);
    System.out.println(dlqArn);

    // Create the main queue with DLQ configuration
    String redrivePolicy = String.format("{\"maxReceiveCount\":\"5\",\"deadLetterTargetArn\":\"%s\"}", dlqArn);
    Map<String, String> map = new HashMap<>();
    map.put(QueueAttributeName.REDRIVE_POLICY.toString(), redrivePolicy);
    SetQueueAttributesRequest queueAttributesRequest = SetQueueAttributesRequest.builder()
            .queueUrl(queueUrl+ createDLQ.getParentQ())
            .attributesWithStrings(map).build();
    SetQueueAttributesResponse response = sqsClient.setQueueAttributes(queueAttributesRequest);
    String mainQueueUrl = response.toString();
    System.out.println("Main Queue URL: " + mainQueueUrl);

}


}
