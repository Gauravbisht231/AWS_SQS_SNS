package com.example.aws.contollers;

import com.example.aws.dto.SubsEmail;
import com.example.aws.services.emailService.SubscribeEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscribeEmailController {
    @Autowired
    private SubscribeEmail subscribeEmail;

    @PostMapping("/subscribe/email")
    public  String subscribeEmail(@RequestBody SubsEmail subsEmail){
        return this.subscribeEmail.subEmail(subsEmail);
    }
}
