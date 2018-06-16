package io.sandstorm.controller.portadapter.persist.mgo.spring.autoconfig;

import com.mongodb.MongoClient;
import io.sandstorm.controller.portadapter.persist.mgo.spring.DataStoreFactoryBean;
import io.sandstorm.controller.portadapter.persist.mgo.spring.MongoClientFactoryBean;
import io.sandstorm.controller.portadapter.persist.mgo.spring.MorphiaFactoryBean;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Mongo dataSource autoConfiguration in spring boot.
 */
//@Configuration
//@EnableAutoConfiguration
//@EnableConfigurationProperties({MongoDataSourceProperties.class,
//        MongoMorphiaProperties.class})
public class MongoAutoConfig {

    @Resource
    private MongoDataSourceProperties properties;

    @Resource
    private MongoMorphiaProperties morphiaProperties;

    @Bean
    @ConditionalOnMissingBean
    public MongoClientFactoryBean mongoClientFactoryBean() throws Exception {
        MongoClientFactoryBean mongoClientFactoryBean = new MongoClientFactoryBean();
        mongoClientFactoryBean.setHost(properties.getHost());
        mongoClientFactoryBean.setPort(properties.getPort());
        return mongoClientFactoryBean;
    }

    @Bean(destroyMethod = "close")
    @ConditionalOnBean(MongoClientFactoryBean.class)
    public MongoClient mongoClient() throws Exception {
        return mongoClientFactoryBean().getObject();
    }

    @Bean
    @ConditionalOnMissingBean
    public MorphiaFactoryBean morphiaFactoryBean() {
        MorphiaFactoryBean morphiaFactoryBean = new MorphiaFactoryBean();
        if (!StringUtils.isEmpty(morphiaProperties.getEntityPackages())) {
            morphiaFactoryBean.setEntityPackages(morphiaProperties.getEntityPackages());
        }
        morphiaFactoryBean.setMapPackages(morphiaProperties.getMapPackages());
        morphiaFactoryBean.setMapClasses(morphiaProperties.getMapClasses());
        morphiaFactoryBean.setIgnoreInvalidClasses(morphiaProperties.isIgnoreInvalidClasses());
        return morphiaFactoryBean;
    }

    @Bean
    @ConditionalOnBean(MorphiaFactoryBean.class)
    public Morphia morphia() throws Exception {
        return morphiaFactoryBean().getObject();
    }

    @Bean
    @ConditionalOnBean(Morphia.class)
    public DataStoreFactoryBean dataStoreFactoryBean() throws Exception {
        DataStoreFactoryBean dataStoreFactoryBean = new DataStoreFactoryBean();
        dataStoreFactoryBean.setDbName(properties.getDbName());
        dataStoreFactoryBean.setMongo(mongoClient());
        dataStoreFactoryBean.setMorphia(morphia());
        return dataStoreFactoryBean;
    }

    @Bean
    @ConditionalOnBean(DataStoreFactoryBean.class)
    public Datastore datastore() throws Exception {
        return dataStoreFactoryBean().getObject();
    }
}
