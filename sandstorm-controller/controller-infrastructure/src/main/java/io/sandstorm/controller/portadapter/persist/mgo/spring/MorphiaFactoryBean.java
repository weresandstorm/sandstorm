package io.sandstorm.controller.portadapter.persist.mgo.spring;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ObjectFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public class MorphiaFactoryBean extends AbstractFactoryBean<Morphia> {

    private List<String> mapClasses;
    private List<String> mapPackages;
    private boolean ignoreInvalidClasses = true;
    private ObjectFactory objectFactory;

    @Override
    public Class<?> getObjectType() {
        return Morphia.class;
    }

    @Override
    protected Morphia createInstance() throws Exception {
        Morphia morphia = new Morphia();
        if (!CollectionUtils.isEmpty(mapClasses)) {
            for (String entityClass : mapClasses) {
                morphia.map(Class.forName(entityClass));
            }
        }
        if (!CollectionUtils.isEmpty(mapPackages)) {
            for (String packageName : mapPackages) {
                morphia.mapPackage(packageName, ignoreInvalidClasses);
            }
        }

        // Set custom object factory
        if (this.objectFactory != null) {
            morphia.getMapper().getOptions().setObjectFactory(objectFactory);
        }

//        morphia.getMapper().getConverters().addConverter(new EntityIdConverter());

        return morphia;
    }

    public void setMapClasses(List<String> mapClasses) {
        this.mapClasses = mapClasses;
    }

    public void setMapPackages(List<String> mapPackages) {
        this.mapPackages = mapPackages;
    }

    public void setEntityPackages(String entityPackages) {
        Preconditions.checkArgument(!StringUtils.isEmpty(entityPackages), "entityPackages is blank");
        String[] packages = entityPackages.split(",");
        this.mapPackages = Lists.newArrayList(packages);
    }

    public void setIgnoreInvalidClasses(boolean ignoreInvalidClasses) {
        this.ignoreInvalidClasses = ignoreInvalidClasses;
    }

    public void setObjectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

}
