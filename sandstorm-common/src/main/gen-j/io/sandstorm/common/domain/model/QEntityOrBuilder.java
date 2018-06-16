package io.sandstorm.common.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEntityOrBuilder is a Querydsl query type for EntityOrBuilder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEntityOrBuilder extends EntityPathBase<EntityOrBuilder> {

    private static final long serialVersionUID = 223288536L;

    public static final QEntityOrBuilder entityOrBuilder = new QEntityOrBuilder("entityOrBuilder");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath creator = createString("creator");

    public final ComparablePath<org.bson.types.ObjectId> id = createComparable("id", org.bson.types.ObjectId.class);

    public final DateTimePath<java.util.Date> lastUpdated = createDateTime("lastUpdated", java.util.Date.class);

    public QEntityOrBuilder(String variable) {
        super(EntityOrBuilder.class, forVariable(variable));
    }

    public QEntityOrBuilder(Path<? extends EntityOrBuilder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEntityOrBuilder(PathMetadata metadata) {
        super(EntityOrBuilder.class, metadata);
    }

}

