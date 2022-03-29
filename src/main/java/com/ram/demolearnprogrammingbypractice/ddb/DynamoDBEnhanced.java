package com.ram.demolearnprogrammingbypractice.ddb;

import com.ram.demolearnprogrammingbypractice.Greeting;
import com.ram.demolearnprogrammingbypractice.ddb.tables.GreetingItems;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Component("DynamoDBEnhanced")
public class DynamoDBEnhanced {

  // Uses the enhanced client to inject a new post into a DynamoDB table
  public void injectDynamoItem(Greeting item) {

    Region region = Region.US_EAST_1;
    DynamoDbClient ddb = DynamoDbClient.builder()
                                       .region(region)
                                       .credentialsProvider(ProfileCredentialsProvider.create("devuser"))
                                       .build();

    try {

      DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                                                                    .dynamoDbClient(ddb)
                                                                    .build();

      // Create a DynamoDbTable object
      DynamoDbTable<GreetingItems> mappedTable = enhancedClient.table("Greeting",
          TableSchema.fromBean(GreetingItems.class));
      GreetingItems gi = new GreetingItems();
      gi.setName(item.getName());
      gi.setMessage(item.getBody());
      gi.setTitle(item.getTitle());
      gi.setId(item.getId());

      PutItemEnhancedRequest enReq = PutItemEnhancedRequest.builder(GreetingItems.class)
                                                           .item(gi)
                                                           .build();

      mappedTable.putItem(enReq);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}