package com.amigoscode.awsimageupload.configuration;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfiguration {

    //Amazon Simple Storage Service is a service offered by Amazon Web Services
    // that provides object storage through a web service interface
    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "AKIAQNFP6JB3UJ23RFGN",
                "L5qLJWZEfOueqWtK3zk4llx52XxsZ5nnqY3931ws"
        );

        return AmazonS3ClientBuilder
                .standard()
                .withRegion("us-east-2")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

}
