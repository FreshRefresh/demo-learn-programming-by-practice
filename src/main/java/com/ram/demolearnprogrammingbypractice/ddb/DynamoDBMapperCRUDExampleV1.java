package com.ram.demolearnprogrammingbypractice.ddb;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import software.amazon.awssdk.regions.Region;

public class DynamoDBMapperCRUDExampleV1 {

  Region region = Region.US_EAST_1;
  static AmazonDynamoDB client
      = AmazonDynamoDBClientBuilder.standard()
                                   .withRegion(Regions.fromName("us-east-1"))
                                   .withCredentials(new ProfileCredentialsProvider("devuser"))
                                   .build();

  //  AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
//                                                     .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
//                                                     .build()
  public static void main(String[] args) throws IOException {
    testCRUDOperations();
    System.out.println("Example complete!");
  }

  @DynamoDBTable(tableName = "ProductCatalog")
  public static class CatalogItem {

    private Integer id;
    private String title;
    private String ISBN;
    private Set<String> bookAuthors;
    private Long version;

    // Partition key
    @DynamoDBHashKey(attributeName = "Id")
    public Integer getId() {
      return id;
    }

    @DynamoDBVersionAttribute
    public Long getVersion() {
      return version;
    }

    public void setVersion(Long version) {
      this.version = version;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    @DynamoDBAttribute(attributeName = "Title")
    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    @DynamoDBAttribute(attributeName = "ISBN")
    public String getISBN() {
      return ISBN;
    }

    public void setISBN(String ISBN) {
      this.ISBN = ISBN;
    }

    @DynamoDBAttribute(attributeName = "Authors")
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

    CatalogItem item = new CatalogItem();
    item.setId(601);
    item.setTitle("Book 601");
    item.setISBN("611-1111111111");
    item.setBookAuthors(new HashSet<String>(Arrays.asList("Author1", "Author2")));

    // Save the item (book).
    DynamoDBMapper mapper = new DynamoDBMapper(client);
    mapper.save(item);

    // Retrieve the item.
    CatalogItem itemRetrieved = mapper.load(CatalogItem.class, 601);
    System.out.println("Item retrieved:");
    System.out.println(itemRetrieved);

    // Update the item.
    itemRetrieved.setISBN("622-2222222222");
    itemRetrieved.setBookAuthors(new HashSet<String>(Arrays.asList("Author1", "Author3")));
    mapper.save(itemRetrieved);
    System.out.println("Item updated:");
    System.out.println(itemRetrieved);

    // Update the item.
    itemRetrieved.setISBN("622-2222222222");
    itemRetrieved.setVersion(1L);
    itemRetrieved.setBookAuthors(new HashSet<String>(Arrays.asList("Author1", "Author3")));
    mapper.save(itemRetrieved);
    System.out.println("Item updated:");
    System.out.println(itemRetrieved);

    // Retrieve the updated item.
    DynamoDBMapperConfig config = DynamoDBMapperConfig.builder()
                                                      .withConsistentReads(
                                                          DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                                                      .build();
    CatalogItem updatedItem = mapper.load(CatalogItem.class, 601, config);
    System.out.println("Retrieved the previously updated item:");
    System.out.println(updatedItem);

    // Delete the item.
    mapper.delete(updatedItem);

    // Try to retrieve deleted item.
    CatalogItem deletedItem = mapper.load(CatalogItem.class, updatedItem.getId(), config);
    if (deletedItem == null) {
      System.out.println("Done - Sample item is deleted.");
    }
  }
}


