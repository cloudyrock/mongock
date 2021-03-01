package com.github.cloudyrock.mongock.driver.mongodb.v3.driver;

import com.github.cloudyrock.mongock.driver.api.driver.ForbiddenParametersMap;
import com.github.cloudyrock.mongock.driver.api.entry.ChangeEntry;
import com.github.cloudyrock.mongock.driver.api.entry.ChangeEntryService;
import com.github.cloudyrock.mongock.driver.mongodb.v3.changelogs.runalways.MongockV3LegacyMigrationChangeRunAlwaysLog;
import com.github.cloudyrock.mongock.driver.mongodb.v3.changelogs.runonce.MongockV3LegacyMigrationChangeLog;
import com.github.cloudyrock.mongock.driver.mongodb.v3.repository.Mongo3ChangeEntryRepository;
import com.github.cloudyrock.mongock.utils.annotation.NotThreadSafe;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

@NotThreadSafe
public class MongoCore3Driver extends MongoCore3DriverBase<ChangeEntry> {

  private static final ForbiddenParametersMap FORBIDDEN_PARAMETERS_MAP = new ForbiddenParametersMap();

  protected Mongo3ChangeEntryRepository<ChangeEntry> changeEntryRepository;

  public static MongoCore3Driver withDefaultLock(MongoClient mongoClient, String databaseName) {
    return new MongoCore3Driver(mongoClient, databaseName, 3L, 4L, 3);
  }

  public static MongoCore3Driver withLockSetting(MongoClient mongoClient,
                                                 String databaseName,
                                                 long lockAcquiredForMinutes,
                                                 long maxWaitingForLockMinutes,
                                                 int maxTries) {
    return new MongoCore3Driver(mongoClient, databaseName, lockAcquiredForMinutes, maxWaitingForLockMinutes, maxTries);
  }

  // For children classes like SpringData drivers
  protected MongoCore3Driver(MongoDatabase mongoDatabase,
                             long lockAcquiredForMinutes,
                             long maxWaitingForLockMinutes,
                             int maxTries) {
    super(mongoDatabase, lockAcquiredForMinutes, maxWaitingForLockMinutes, maxTries);
  }


  protected MongoCore3Driver(MongoClient mongoClient,
                             String databaseName,
                             long lockAcquiredForMinutes,
                             long maxWaitingForLockMinutes,
                             int maxTries) {
    super(mongoClient, databaseName, lockAcquiredForMinutes, maxWaitingForLockMinutes, maxTries);
  }

  @Override
  public ChangeEntryService<ChangeEntry> getChangeEntryService() {
    if (changeEntryRepository == null) {
      this.changeEntryRepository = new Mongo3ChangeEntryRepository<>(mongoDatabase.getCollection(changeLogCollectionName), indexCreation, getReadWriteConfiguration());
    }
    return changeEntryRepository;
  }

  @Override
  public ForbiddenParametersMap getForbiddenParameters() {
    return FORBIDDEN_PARAMETERS_MAP;
  }

  @Override
  public Class getLegacyMigrationChangeLogClass(boolean runAlways) {
    return runAlways ? MongockV3LegacyMigrationChangeRunAlwaysLog.class : MongockV3LegacyMigrationChangeLog.class;
  }
}
