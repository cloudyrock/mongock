package com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.driver;


import com.github.cloudyrock.mongock.driver.mongodb.springdata.v2.SpringDataMongoV2Driver;
import com.github.cloudyrock.mongock.driver.mongodb.test.template.MongoDriverITestBase;
import com.github.cloudyrock.mongock.driver.mongodb.test.template.util.MongoDBDriverTestAdapter;
import org.springframework.data.mongodb.core.MongoTemplate;

public class SpringDataMongo2DriverITest extends MongoDriverITestBase {

  @Override
  protected SpringDataMongoV2Driver getDriverWithTransactionDisabled() {
    SpringDataMongoV2Driver driver = SpringDataMongoV2Driver.withDefaultLock(getMongoTemplate());
    driver.setChangeLogCollectionName(CHANGELOG_COLLECTION_NAME);
    return driver;
  }

  @Override
  protected MongoDBDriverTestAdapter getAdapter(String collectionName) {
    return new SpringData2DriverTestAdapterImpl(getDataBase().getCollection(collectionName));
  }

  private MongoTemplate getMongoTemplate() {
    return new MongoTemplate(this.getMongoClient(), this.getDataBase().getName());
  }
}
