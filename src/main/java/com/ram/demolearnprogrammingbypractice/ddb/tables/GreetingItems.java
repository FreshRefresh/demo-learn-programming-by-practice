package com.ram.demolearnprogrammingbypractice.ddb.tables;

import software.amazon.awssdk.enhanced.dynamodb.extensions.annotations.DynamoDbVersionAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class GreetingItems {

  //Set up Data Members that correspond to columns in the Greeting table
  private String id;
  private String name;
  private String message;
  private String title;
  private Long version;

  public GreetingItems() {
  }

  @DynamoDbPartitionKey
  public String getId() {
    return this.id;
  }

  @DynamoDbVersionAttribute
  public Long getVersion() { return version; }

  public void setVersion(Long version) { this.version = version;}

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}