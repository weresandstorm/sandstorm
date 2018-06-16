package io.sandstorm.controller.domain.resource;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTestScriptDiscard is a Querydsl query type for TestScriptDiscard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestScriptDiscard extends EntityPathBase<TestScriptDiscard> {

    private static final long serialVersionUID = 217367466L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTestScriptDiscard testScriptDiscard = new QTestScriptDiscard("testScriptDiscard");

    public final io.sandstorm.common.domain.model.QEntityOrBuilder _super = new io.sandstorm.common.domain.model.QEntityOrBuilder(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    public final DateTimePath<java.util.Date> discardedAt = createDateTime("discardedAt", java.util.Date.class);

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final QTestScript script;

    public QTestScriptDiscard(String variable) {
        this(TestScriptDiscard.class, forVariable(variable), INITS);
    }

    public QTestScriptDiscard(Path<? extends TestScriptDiscard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTestScriptDiscard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTestScriptDiscard(PathMetadata metadata, PathInits inits) {
        this(TestScriptDiscard.class, metadata, inits);
    }

    public QTestScriptDiscard(Class<? extends TestScriptDiscard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.script = inits.isInitialized("script") ? new QTestScript(forProperty("script")) : null;
    }

}

