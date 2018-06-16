package io.sandstorm.controller.portadapter.persist.mgo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStatus is a Querydsl query type for Status
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QStatus extends BeanPath<Status> {

    private static final long serialVersionUID = 1138782374L;

    public static final QStatus status = new QStatus("status");

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    public final StringPath name = createString("name");

    public QStatus(String variable) {
        super(Status.class, forVariable(variable));
    }

    public QStatus(Path<Status> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStatus(PathMetadata metadata) {
        super(Status.class, metadata);
    }

}

