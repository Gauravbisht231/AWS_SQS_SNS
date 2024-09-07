package com.example.aws.contollers;

import com.example.aws.dto.SubsSqs;
import com.example.aws.services.sqsService.SubscribeSqs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscribeSqsController {
    @Autowired
    private SubscribeSqs subscribeSqs;

    @PostMapping("/subscribe/sqs")
    public void subscribesqs(@RequestBody SubsSqs subsSqs){
        this.subscribeSqs.subscribeSqs(subsSqs);
    }
}
