package io.sandstorm.controller.domain.resource;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QLoadInjector is a Querydsl query type for LoadInjector
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QLoadInjector extends EntityPathBase<LoadInjector> {

    private static final long serialVersionUID = 1183926993L;

    public static final QLoadInjector loadInjector = new QLoadInjector("loadInjector");

    public final io.sandstorm.common.domain.model.QVersionedEntityOrBuilder _super = new io.sandstorm.common.domain.model.QVersionedEntityOrBuilder(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    public final StringPath hostname = createString("hostname");

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    public final StringPath intranetIp = createString("intranetIp");

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final NumberPath<Integer> port = createNumber("port", Integer.class);

    public final StringPath publicIp = createString("publicIp");

    public final EnumPath<LoadInjector.State> state = createEnum("state", LoadInjector.State.class);

    public final BooleanPath usable = createBoolean("usable");

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QLoadInjector(String variable) {
        super(LoadInjector.class, forVariable(variable));
    }

    public QLoadInjector(Path<? extends LoadInjector> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLoadInjector(PathMetadata metadata) {
        super(LoadInjector.class, metadata);
    }

}

