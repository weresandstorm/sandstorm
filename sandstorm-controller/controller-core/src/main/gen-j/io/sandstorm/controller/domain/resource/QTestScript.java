package io.sandstorm.controller.domain.resource;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTestScript is a Querydsl query type for TestScript
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestScript extends EntityPathBase<TestScript> {

    private static final long serialVersionUID = 1783158356L;

    public static final QTestScript testScript = new QTestScript("testScript");

    public final io.sandstorm.common.domain.model.QEntityOrBuilder _super = new io.sandstorm.common.domain.model.QEntityOrBuilder(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    public final StringPath desc = createString("desc");

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    public final StringPath jarAlias = createString("jarAlias");

    public final StringPath jarUrl = createString("jarUrl");

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final NumberPath<Integer> rev = createNumber("rev", Integer.class);

    public QTestScript(String variable) {
        super(TestScript.class, forVariable(variable));
    }

    public QTestScript(Path<? extends TestScript> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTestScript(PathMetadata metadata) {
        super(TestScript.class, metadata);
    }

}

