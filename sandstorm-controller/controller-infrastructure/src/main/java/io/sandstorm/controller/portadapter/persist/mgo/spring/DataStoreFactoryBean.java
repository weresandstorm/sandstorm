package io.sandstorm.controller.portadapter.persist.mgo.spring;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.config.AbstractFactoryBean;

public class DataStoreFactoryBean extends AbstractFactoryBean<Datastore> {

    private Morphia morphia;
    private Mongo mongo;
    private String dbName;

    @Override
    public Class<?> getObjectType() {
        return Datastore.class;
    }

    @Override
    protected Datastore createInstance() throws Exception {
        if (!(mongo instanceof MongoClient)) {
            throw new RuntimeException("Expected MongoClient instance, but actual is Mongo instance");
        }

        MongoClient mongoClient = (MongoClient) mongo;
        Datastore dataStore = morphia.createDatastore(mongoClient, dbName);
        //dataStore.ensureCaps();
        //dataStore.ensureIndexes();
        return dataStore;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        if (mongo == null) {
            throw new IllegalStateException("mongo is not set");
        }
        if (morphia == null) {
            throw new IllegalStateException("morphia is not set");
        }
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public void setMongo(Mongo mongo) {
        this.mongo = mongo;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

}