package com.ram.demolearnprogrammingbypractice.ddb;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.extensions.annotations.DynamoDbVersionAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

/**
 * https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2/example_code/dynamodb
 */
public class DynamoDBMapperCRUDExampleV2 {

  public static DynamoDbClient dynamoDbClient
      = DynamoDbClient.builder()
                      .endpointOverride(URI.create("http://localhost:8000"))
                      // The region is meaningless for local DynamoDb but required for client builder validation
                      .region(Region.US_WEST_2)
                      .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("dummy-key", "dummy-secret")))
                      .build();

  public static DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                                                                              .dynamoDbClient(dynamoDbClient)
                                                                              .build();
  public static DynamoDbTable<ProductCatalog> dynamoDbTable = enhancedClient.table("ProductCatalog", TableSchema.fromBean(ProductCatalog.class));

  public static void main(String[] args) throws IOException {
    testCRUDOperations();
    System.out.println("Example complete!");
  }

  @DynamoDbBean
  public static class ProductCatalog {

    private Integer id;
    private String title;
    private String ISBN;
    private Set<String> bookAuthors;
    private Long version;

    // Partition key
    @DynamoDbPartitionKey
    public Integer getId() {
      return id;
    }

    @DynamoDbVersionAttribute
    public Long getVersion() {
      return version;
    }

    public void setVersion(Long version) {
      this.version = version;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    @DynamoDbAttribute(value = "Title")
    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    @DynamoDbAttribute(value = "ISBN")
    public String getISBN() {
      return ISBN;
    }

    public void setISBN(String ISBN) {
      this.ISBN = ISBN;
    }

    @DynamoDbAttribute(value = "Authors")
    public Set<String> getBookAuthors() {
      return bookAuthors;
    }

    public void setBookAuthors(Set<String> bookAuthors) {
      this.bookAuthors = bookAuthors;
    }

    @Override
    public String toString() {
      return "Book [ISBN=" + ISBN + ", bookAuthors=" + bookAuthors + ", id=" + id + ", title="
          + title + "]";
    }
  }

  private static void testCRUDOperations() {

    ProductCatalog item = new ProductCatalog();
    item.setId(601);
    item.setTitle("Book 601");
    item.setISBN("611-1111111111");
    item.setBookAuthors(new HashSet<String>(Arrays.asList("Author1", "Author2")));

    // Save the item (book).
    //  DynamoDBMapper mapper = new DynamoDBMapper(client);

    dynamoDbTable.putItem(PutItemEnhancedRequest.builder(ProductCatalog.class)
                                                .item(item)
                                                .build());

    // Retrieve the item.
    ProductCatalog itemRetrieved
        = dynamoDbTable.getItem(GetItemEnhancedRequest.builder().key(Key.builder().partitionValue(
        item.getId()).build()).build());

    System.out.println("Item retrieved:");
    System.out.println(itemRetrieved);

    // Update the item.
    itemRetrieved.setISBN("622-2222222222");
    itemRetrieved.setBookAuthors(new HashSet<String>(Arrays.asList("Author1", "Author3")));
    dynamoDbTable.putItem(PutItemEnhancedRequest.builder(ProductCatalog.class)
                                                .item(itemRetrieved)
                                                .build());
    System.out.println("Item updated:");
    System.out.println(itemRetrieved);

    // Update the item.
//    itemRetrieved.setISBN("622-2222222222");
//    itemRetrieved.setVersion(1L);
//    itemRetrieved.setBookAuthors(new HashSet<String>(Arrays.asList("Author1", "Author3")));
//    dynamoDbTable.putItem(PutItemEnhancedRequest.builder(ProductCatalog.class)
//                                                .item(itemRetrieved)
//                                                .build());
    System.out.println("Item updated:");
    System.out.println(itemRetrieved);

    // Retrieve the updated item.
//    DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
//                                                      .withConsistentReads(
//                                                          DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
//                                                      .build();
//    ProductCatalog updatedItem = mapper.load(ProductCatalog.class, 601, config);
    System.out.println("Retrieved the previously updated item:");
    //   System.out.println(updatedItem);

//    // Delete the item.
//    mapper.delete(updatedItem);
//
//    // Try to retrieve deleted item.
//    CatalogItem deletedItem = mapper.load(ProductCatalog.class, updatedItem.getId(), config);
//    if (deletedItem == null) {
//      System.out.println("Done - Sample item is deleted.");
//    }
  }
}


