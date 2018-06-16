package io.sandstorm.controller.domain.resource;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDataSet is a Querydsl query type for DataSet
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDataSet extends EntityPathBase<DataSet> {

    private static final long serialVersionUID = -693556895L;

    public static final QDataSet dataSet = new QDataSet("dataSet");

    public final io.sandstorm.common.domain.model.QEntityOrBuilder _super = new io.sandstorm.common.domain.model.QEntityOrBuilder(this);

    public final NumberPath<Integer> chunksCount = createNumber("chunksCount", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    public final StringPath desc = createString("desc");

    public final StringPath feederFileName = createString("feederFileName");

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final NumberPath<Integer> rev = createNumber("rev", Integer.class);

    public final EnumPath<DataSet.SourceType> sourceType = createEnum("sourceType", DataSet.SourceType.class);

    public final StringPath sourceUrl = createString("sourceUrl");

    public QDataSet(String variable) {
        super(DataSet.class, forVariable(variable));
    }

    public QDataSet(Path<? extends DataSet> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDataSet(PathMetadata metadata) {
        super(DataSet.class, metadata);
    }

}

