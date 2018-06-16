package io.sandstorm.controller.portadapter.persist.mgo.spring.autoconfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Mongo client configuration properties but not all
 */
//@ConfigurationProperties(prefix = "mongodb")
public class MongoDataSourceProperties implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private String host;
    private int port;
    private String dbName;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }
}
