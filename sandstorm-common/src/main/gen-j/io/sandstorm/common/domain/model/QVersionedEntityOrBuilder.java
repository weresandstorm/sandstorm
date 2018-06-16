package io.sandstorm.common.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QVersionedEntityOrBuilder is a Querydsl query type for VersionedEntityOrBuilder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QVersionedEntityOrBuilder extends EntityPathBase<VersionedEntityOrBuilder> {

    private static final long serialVersionUID = -410360517L;

    public static final QVersionedEntityOrBuilder versionedEntityOrBuilder = new QVersionedEntityOrBuilder("versionedEntityOrBuilder");

    public final QEntityOrBuilder _super = new QEntityOrBuilder(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QVersionedEntityOrBuilder(String variable) {
        super(VersionedEntityOrBuilder.class, forVariable(variable));
    }

    public QVersionedEntityOrBuilder(Path<? extends VersionedEntityOrBuilder> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVersionedEntityOrBuilder(PathMetadata metadata) {
        super(VersionedEntityOrBuilder.class, metadata);
    }

}

