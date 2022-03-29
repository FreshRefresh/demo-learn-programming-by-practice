package com.ram.demolearnprogrammingbypractice.ddb.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

/**
 * https://docs.aws.amazon.com/code-samples/latest/catalog/javav2-dynamodb-Makefile.html
 * @see <a herf ="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/migration.html" >Dynamo DB</a>
 */
@Configuration
public class DynamoDBConfiguration {

  @Bean
  public DynamoDbClient createDynamoDbClient() {
    DynamoDbClient dynamoDbClient
        = DynamoDbClient.builder()
                        .endpointOverride(URI.create("http://localhost:8000"))
                        .region(Region.US_WEST_2)
                        .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("dummy-key", "dummy-secret")))
                        .build();
    //DynamoDbClient.builder().credentialsProvider(ProfileCredentialsProvider.create("devuser")).build();
    return dynamoDbClient;
  }

  @Bean
  public DynamoDbEnhancedClient createDynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
    DynamoDbEnhancedClient dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build();
    return dynamoDbEnhancedClient;
  }

  @Bean
  public AmazonDynamoDB createAmazonDynamoDBClientBuilder() {
    return AmazonDynamoDBClientBuilder.standard()
                                      .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
                                      .build();
  }

  @Bean
  /**
   * https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Programming.SDKs.Interfaces.Document.html
   *
   * DynamoDB acts as a wrapper around the low-level client (AmazonDynamoDB)
   */
  public DynamoDB createDynamoDB(@Autowired AmazonDynamoDB amazonDynamoDB) {
    return new DynamoDB(amazonDynamoDB);
  }

  /**
   * https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Programming.SDKs.Interfaces.Mapper.html
   *
   * @param amazonDynamoDB
   * @return
   */
  @Bean
  public DynamoDBMapper createDynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
    return new DynamoDBMapper(amazonDynamoDB);
  }
}
