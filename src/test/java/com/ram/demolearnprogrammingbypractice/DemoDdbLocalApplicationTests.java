package com.ram.demolearnprogrammingbypractice;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DemoDdbLocalApplicationTests {

  private DynamoDBProxyServer dynamoDBProxyServer;

  @BeforeEach
  void setUp() {
    try {
      // setup location of native libs path
      System.setProperty("sqlite4java.library.path", "native-libs");

      dynamoDBProxyServer = ServerRunner.createServerFromCommandLineArgs(new String[]{"-dbPath", "ddb-data"
          , "-port", "8000"});
      dynamoDBProxyServer.start();
      AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                                                         .withCredentials(new ProfileCredentialsProvider("devuser"))
                                                         .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
                                                         .build();

      DynamoDB dynamoDB = new DynamoDB(client);
      List<com.amazonaws.services.dynamodbv2.model.AttributeDefinition> attributeDefinitions = new ArrayList<>();
      attributeDefinitions.add(new com.amazonaws.services.dynamodbv2.model.AttributeDefinition().withAttributeName("Id").withAttributeType("N"));

      List<KeySchemaElement> keySchema = new ArrayList<>();
      keySchema.add(new com.amazonaws.services.dynamodbv2.model.KeySchemaElement().withAttributeName("Id").withKeyType(KeyType.HASH));

      CreateTableRequest request = new CreateTableRequest()
          .withTableName("Ram_Table")
          .withKeySchema(keySchema)
          .withAttributeDefinitions(attributeDefinitions)
          .withProvisionedThroughput(new ProvisionedThroughput()
              .withReadCapacityUnits(5L)
              .withWriteCapacityUnits(6L));

      Table table = dynamoDB.createTable(request);

      table.waitForActive();

    } catch (ParseException e) {
      e.printStackTrace();
    } catch (Exception exception) {
      exception.printStackTrace();
    }

  }

  @AfterEach
  void tearDown() {
    try {
      dynamoDBProxyServer.stop();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void contextLoads() {

  }

}
