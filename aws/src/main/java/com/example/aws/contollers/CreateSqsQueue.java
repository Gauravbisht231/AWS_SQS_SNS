package com.example.aws.contollers;

import com.example.aws.dto.CreateDLQ;
import com.example.aws.services.sqsService.CreateSqs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateSqsQueue {
    @Autowired
    private CreateSqs createQueue;
    @PostMapping("/createQ")
    public String createQ(@RequestBody String Qname){
        return this.createQueue.createQueue(Qname);
    }

    @PostMapping("/createDlq")
    public void createDlq(@RequestBody CreateDLQ createDLQ){
        this.createQueue.CreateDLQ(createDLQ);
    }
}
