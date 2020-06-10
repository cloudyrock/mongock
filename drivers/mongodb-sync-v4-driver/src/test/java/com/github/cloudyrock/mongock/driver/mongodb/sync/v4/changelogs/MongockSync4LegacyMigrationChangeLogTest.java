package com.github.cloudyrock.mongock.driver.mongodb.sync.v4.changelogs;

import com.github.cloudyrock.mongock.driver.mongodb.sync.v4.MongoDbSync4DriverTestAdapterImpl;
import com.github.cloudyrock.mongock.driver.mongodb.sync.v4.driver.MongoSync4Driver;
import com.github.cloudyrock.mongock.driver.mongodb.test.template.MongoDriverITestBase;
import com.github.cloudyrock.mongock.driver.mongodb.test.template.util.MongoDbDriverTestAdapter;
import org.junit.Test;

public class MongockSync4LegacyMigrationChangeLogTest extends MongoDriverITestBase {

  @Override
  protected MongoSync4Driver getDriver() {
    MongoSync4Driver driver = new MongoSync4Driver(this.getDataBase());
    driver.setChangeLogCollectionName(CHANGELOG_COLLECTION_NAME);
    return driver;
  }

  @Override
  protected MongoDbDriverTestAdapter getAdapter(String collectionName) {
    return new MongoDbSync4DriverTestAdapterImpl(getDataBase().getCollection(collectionName));
  }

  @Test
  public void shouldMigrateLegacyChangeLogs() {

  }

}
