package com.example.aws.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsClient;


@Configuration
public class Config {
        @Value("${access_key}")
        private String accesskey;
        @Value("${secret_key}")
        private String secretkey;

        @Bean
        public SnsClient snsClientBuilder() {
            return  SnsClient.builder().credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(accesskey, secretkey)
                    ))
                    .region(Region.US_EAST_1)
                    .build();
        }
    @Bean
    public SqsClient sqsClientBuilder() {
        return SqsClient.builder().credentialsProvider(StaticCredentialsProvider.create(
            AwsBasicCredentials.create(accesskey, secretkey)
        )).region(Region.US_EAST_1).build();
        }

    }


