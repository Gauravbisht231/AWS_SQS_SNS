//package com.example.aws.contollers;
//import com.example.aws.dto.PubMessageDto;
//import com.example.aws.dto.SubsEmail;
//import com.example.aws.dto.SubsSqs;
//import com.example.aws.services.emailService.SubscribeEmail;
//import com.example.aws.services.snsService.CreateSns;
//import com.example.aws.services.snsService.PublishMessage;
//import com.example.aws.services.sqsService.CreateSqs;
//import com.example.aws.services.sqsService.SubscribeSqs;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class Controllers {
//    @Autowired
////    private AwsService awsService;
//    private SubscribeEmail subscribeEmail;
//
//    @Autowired
//    private CreateSqs createQueue;
//    @Autowired
//    private PublishMessage publishMessage;
//    @Autowired
//    private SubscribeSqs subscribeSqs;
//
//    @Autowired
//    private CreateSns createSnsTopic;
//    @PostMapping("/createtopic")
//    public String createtopic(@RequestBody String topic){
//        return this.createSnsTopic.createSnsTopic(topic);
//    }
//    @PostMapping("/createQ")
//    public String createQ(@RequestBody String Qname){
//    return this.createQueue.createQueue(Qname);
//    }
//
//    @PostMapping("/createtopic/message")
//    public void TopicMessage(@RequestBody PubMessageDto pubMessageDto){
//        this.publishMessage.pubTopic(pubMessageDto);
//    }
//
//    @PostMapping("/subscribe/sqs")
//    public void subscribesqs(@RequestBody SubsSqs subsSqs){
//        this.subscribeSqs.subscribeSqs(subsSqs);
//    }
//// subscribing to email:
//    @PostMapping("/subscribe/email")
//    public  String subscribeEmail(@RequestBody SubsEmail subsEmail){
//        return this.subscribeEmail.subEmail(subsEmail);
//    }
//
//
//
//
//
//
//
//}
