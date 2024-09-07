//package com.example.aws.services.polling;
//
//import com.example.aws.dto.PollingDto;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import software.amazon.awssdk.core.exception.SdkException;
//import software.amazon.awssdk.services.sqs.SqsClient;
//import software.amazon.awssdk.services.sqs.model.Message;
//import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
//import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
//
//import java.util.List;
//
//@Service
//public class BatchPolling {
//
//    private static final Logger logger = LoggerFactory.getLogger(BatchPolling.class);
//
//    private final SqsClient sqsClient;
//
//    @Value("${aws.sqs.queue-url}")
//    private String url;
//
//    public BatchPolling(SqsClient sqsClient) {
//        this.sqsClient = sqsClient;
//    }
//
//    public List<Message> getMsg(PollingDto pollingDto) {
//        try {
//            ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
//                    .queueUrl(url + pollingDto.getQueueName())
//                    .maxNumberOfMessages(6)
//                    .waitTimeSeconds(10)
//                    .messageAttributeNames("All")
//                    .build();
//
//            ReceiveMessageResponse receiveMessageResponse = sqsClient.receiveMessage(receiveMessageRequest);
//            List<Message> messages = receiveMessageResponse.messages();
//
//            for (Message msg : messages) {
//                logger.info("Received message: {}: {}", msg.messageId(), msg.body());
//            }
//
//            return messages;
//
//        } catch (SdkException e) {
//            logger.error("Couldn't receive messages from queue: {}", url, e);
//            throw e;
//        }
//    }
//}

package com.example.aws.services.polling;

import com.example.aws.dto.PollingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class BatchPolling {

    private static final Logger logger = LoggerFactory.getLogger(BatchPolling.class);

    private final SqsClient sqsClient;

    @Value("${aws.sqs.queue-url}")
    private String url;

    public BatchPolling(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public List<Message> getMsg(PollingDto pollingDto) {
        List<Message> allMessages = new ArrayList<>();

        while (allMessages.size() < 6) {
            try {
                ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                        .queueUrl(url + pollingDto.getQueueName())
                        .maxNumberOfMessages(6) // Request up to 6 messages
                        .waitTimeSeconds(10) // Wait time to allow long polling
                        .messageAttributeNames("All")
                        .build();

                ReceiveMessageResponse receiveMessageResponse = sqsClient.receiveMessage(receiveMessageRequest);
                List<Message> messages = receiveMessageResponse.messages();

                if (messages.isEmpty()) {
                    logger.info("No messages received from queue: {}", url + pollingDto.getQueueName());
                    // Break out if no messages are available and you already have some messages
                    if (!allMessages.isEmpty()) {
                        break;
                    }
                } else {
                    allMessages.addAll(messages);
                    for (Message msg : messages) {
                        logger.info("Received message: {}: {}", msg.messageId(), msg.body());
                    }
                }


            } catch (SdkException e) {
                logger.error("Couldn't receive messages from queue: {}", url + pollingDto.getQueueName(), e);
                throw e;
            }
        }
        if (allMessages.size() > 6) {
            return allMessages.subList(0, 6);
        }

        return allMessages;
    }
}
