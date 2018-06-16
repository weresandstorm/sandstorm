package io.sandstorm.controller.domain.job;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJobExecution is a Querydsl query type for JobExecution
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJobExecution extends EntityPathBase<JobExecution> {

    private static final long serialVersionUID = 328630677L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobExecution jobExecution = new QJobExecution("jobExecution");

    public final QBasicExecution _super = new QBasicExecution(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    public final io.sandstorm.controller.domain.resource.QDataSet dataSet;

    //inherited
    public final BooleanPath ended = _super.ended;

    //inherited
    public final DateTimePath<java.util.Date> endedAt = _super.endedAt;

    public final EnumPath<ExecPlan.ExecMode> execMode = createEnum("execMode", ExecPlan.ExecMode.class);

    //inherited
    public final StringPath failureInfo = _super.failureInfo;

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    public final ComparablePath<org.bson.types.ObjectId> jobId = createComparable("jobId", org.bson.types.ObjectId.class);

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final SimplePath<LoadProfile> loadProfile = createSimple("loadProfile", LoadProfile.class);

    public final StringPath reportUrl = createString("reportUrl");

    public final io.sandstorm.controller.domain.resource.QTestScript script;

    public final StringPath simulationToRun = createString("simulationToRun");

    //inherited
    public final DateTimePath<java.util.Date> startedAt = _super.startedAt;

    //inherited
    public final EnumPath<ExecutionStatus> status = _super.status;

    //inherited
    public final ListPath<ExecutionStatus, EnumPath<ExecutionStatus>> statusFlow = _super.statusFlow;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QJobExecution(String variable) {
        this(JobExecution.class, forVariable(variable), INITS);
    }

    public QJobExecution(Path<? extends JobExecution> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJobExecution(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJobExecution(PathMetadata metadata, PathInits inits) {
        this(JobExecution.class, metadata, inits);
    }

    public QJobExecution(Class<? extends JobExecution> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dataSet = inits.isInitialized("dataSet") ? new io.sandstorm.controller.domain.resource.QDataSet(forProperty("dataSet")) : null;
        this.script = inits.isInitialized("script") ? new io.sandstorm.controller.domain.resource.QTestScript(forProperty("script")) : null;
    }

}

