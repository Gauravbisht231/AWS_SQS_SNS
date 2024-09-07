package com.example.aws.dto;

import lombok.Data;

@Data
public class CreateDLQ {
    private String parentQ;
    private String childQ;

}
