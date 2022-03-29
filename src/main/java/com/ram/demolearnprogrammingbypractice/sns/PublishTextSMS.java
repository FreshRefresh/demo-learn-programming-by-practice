package com.ram.demolearnprogrammingbypractice.sns;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SnsException;

@Component("PublishTextSMS")
public class PublishTextSMS {

  public void sendMessage(String id) {

    Region region = Region.US_EAST_1;
    SnsClient snsClient = SnsClient.builder()
                                   .region(region)
                                   .credentialsProvider(ProfileCredentialsProvider.create("devuser"))
                                   .build();

    String message = "A new item with ID value "+ id +" was added to the DynamoDB table";
    String phoneNumber="<ENTER MOBILE PHONE NUMBER>"; //Replace with a mobile phone number

    try {
      PublishRequest request = PublishRequest.builder()
                                             .message(message)
                                             .phoneNumber(phoneNumber)
                                             .build();

      snsClient.publish(request);

    } catch (SnsException e) {
      System.err.println(e.awsErrorDetails().errorMessage());
      System.exit(1);
    }
  }
}
