package io.sandstorm.controller.portadapter.persist.mgo.spring.autoconfig;

import org.mongodb.morphia.ObjectFactory;

import java.util.List;

/**
 *  mongo morphia config
 */
//@ConfigurationProperties(prefix = "mongodb.morphia")
public class MongoMorphiaProperties {

    private String entityPackages;

    private List<String> mapClasses;

    private List<String> mapPackages;

    private boolean ignoreInvalidClasses = true;

    private ObjectFactory objectFactory;

    protected String getEntityPackages() {
        return entityPackages;
    }

    protected void setEntityPackages(String entityPackages) {
        this.entityPackages = entityPackages;
    }

    protected List<String> getMapClasses() {
        return mapClasses;
    }

    protected void setMapClasses(List<String> mapClasses) {
        this.mapClasses = mapClasses;
    }

    protected List<String> getMapPackages() {
        return mapPackages;
    }

    protected void setMapPackages(List<String> mapPackages) {
        this.mapPackages = mapPackages;
    }

    protected boolean isIgnoreInvalidClasses() {
        return ignoreInvalidClasses;
    }

    protected void setIgnoreInvalidClasses(boolean ignoreInvalidClasses) {
        this.ignoreInvalidClasses = ignoreInvalidClasses;
    }

    protected ObjectFactory getObjectFactory() {
        return objectFactory;
    }

    protected void setObjectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }
}
