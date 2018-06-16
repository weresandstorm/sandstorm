package io.sandstorm.controller.domain.resource;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDataChunk is a Querydsl query type for DataChunk
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataChunk extends EntityPathBase<DataChunk> {

    private static final long serialVersionUID = -802927700L;

    public static final QDataChunk dataChunk = new QDataChunk("dataChunk");

    public final io.sandstorm.common.domain.model.QEntityOrBuilder _super = new io.sandstorm.common.domain.model.QEntityOrBuilder(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    public final ComparablePath<org.bson.types.ObjectId> dataSetId = createComparable("dataSetId", org.bson.types.ObjectId.class);

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final StringPath signature = createString("signature");

    public final StringPath url = createString("url");

    public QDataChunk(String variable) {
        super(DataChunk.class, forVariable(variable));
    }

    public QDataChunk(Path<? extends DataChunk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDataChunk(PathMetadata metadata) {
        super(DataChunk.class, metadata);
    }

}

