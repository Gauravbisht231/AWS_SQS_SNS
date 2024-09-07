package com.example.aws.contollers;


import com.example.aws.dto.PollingDto;
import com.example.aws.services.polling.BatchPolling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PollingSqs {
@Autowired
    private BatchPolling batchPolling;
@PostMapping("/queue/poll")
    public List<Map<String, String>> receiveMessages(@RequestBody PollingDto pollingDto) {
        List<Message> messages = batchPolling.getMsg(pollingDto);
        return messages.stream()
                .map(msg -> Map.of(
                        "messageId", msg.messageId(),
                        "body", msg.body()))
                .collect(Collectors.toList());
    }
}


