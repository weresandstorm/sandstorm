package io.sandstorm.controller.domain.resource;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDataChunkDiscard is a Querydsl query type for DataChunkDiscard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataChunkDiscard extends EntityPathBase<DataChunkDiscard> {

    private static final long serialVersionUID = 161684306L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDataChunkDiscard dataChunkDiscard = new QDataChunkDiscard("dataChunkDiscard");

    public final io.sandstorm.common.domain.model.QEntityOrBuilder _super = new io.sandstorm.common.domain.model.QEntityOrBuilder(this);

    public final QDataChunk chunk;

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    public final DateTimePath<java.util.Date> discardedAt = createDateTime("discardedAt", java.util.Date.class);

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public QDataChunkDiscard(String variable) {
        this(DataChunkDiscard.class, forVariable(variable), INITS);
    }

    public QDataChunkDiscard(Path<? extends DataChunkDiscard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDataChunkDiscard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDataChunkDiscard(PathMetadata metadata, PathInits inits) {
        this(DataChunkDiscard.class, metadata, inits);
    }

    public QDataChunkDiscard(Class<? extends DataChunkDiscard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chunk = inits.isInitialized("chunk") ? new QDataChunk(forProperty("chunk")) : null;
    }

}

