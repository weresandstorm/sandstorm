package io.sandstorm.controller.domain.job;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTestJob_Builder is a Querydsl query type for Builder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestJob_Builder extends EntityPathBase<TestJob.Builder> {

    private static final long serialVersionUID = -1928873378L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTestJob_Builder builder = new QTestJob_Builder("builder");

    public final io.sandstorm.common.domain.model.QEntityOrBuilder _super = new io.sandstorm.common.domain.model.QEntityOrBuilder(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    public final io.sandstorm.controller.domain.resource.QDataSet dataSet;

    public final SimplePath<java.util.Optional<org.bson.types.ObjectId>> dataSetId = createSimple("dataSetId", java.util.Optional.class);

    public final SimplePath<ExecPlan> execPlan = createSimple("execPlan", ExecPlan.class);

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final SimplePath<LoadProfile> loadProfile = createSimple("loadProfile", LoadProfile.class);

    public final StringPath name = createString("name");

    public final StringPath remark = createString("remark");

    public final io.sandstorm.controller.domain.resource.QTestScript script;

    public final ComparablePath<org.bson.types.ObjectId> scriptId = createComparable("scriptId", org.bson.types.ObjectId.class);

    public final StringPath simulationToRun = createString("simulationToRun");

    public QTestJob_Builder(String variable) {
        this(TestJob.Builder.class, forVariable(variable), INITS);
    }

    public QTestJob_Builder(Path<TestJob.Builder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTestJob_Builder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTestJob_Builder(PathMetadata metadata, PathInits inits) {
        this(TestJob.Builder.class, metadata, inits);
    }

    public QTestJob_Builder(Class<? extends TestJob.Builder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dataSet = inits.isInitialized("dataSet") ? new io.sandstorm.controller.domain.resource.QDataSet(forProperty("dataSet")) : null;
        this.script = inits.isInitialized("script") ? new io.sandstorm.controller.domain.resource.QTestScript(forProperty("script")) : null;
    }

}

