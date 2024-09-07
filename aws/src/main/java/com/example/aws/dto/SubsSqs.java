package com.example.aws.dto;
import lombok.Data;
//import lombok.Getter;

@Data
public class SubsSqs{
    private String topicName;
    private String queueName;
}