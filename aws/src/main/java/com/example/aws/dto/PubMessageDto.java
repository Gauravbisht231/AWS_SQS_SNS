package com.example.aws.dto;

import lombok.Data;

@Data
public class PubMessageDto {
    private String topicName;
    private String message;

}
