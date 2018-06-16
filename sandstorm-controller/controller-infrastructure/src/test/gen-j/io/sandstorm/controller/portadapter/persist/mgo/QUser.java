package io.sandstorm.controller.portadapter.persist.mgo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -2027804289L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final io.sandstorm.common.domain.model.QEntityOrBuilder _super = new io.sandstorm.common.domain.model.QEntityOrBuilder(this);

    public final ListPath<Address, QAddress> addresses = this.<Address, QAddress>createList("addresses", Address.class, QAddress.class, PathInits.DIRECT2);

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final DateTimePath<java.util.Date> created = createDateTime("created", java.util.Date.class);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    public final StringPath firstName = createString("firstName");

    public final QUser friend;

    public final ListPath<User, QUser> friends = this.<User, QUser>createList("friends", User.class, QUser.class, PathInits.DIRECT2);

    public final EnumPath<User.Gender> gender = createEnum("gender", User.Gender.class);

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    public final StringPath lastName = createString("lastName");

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    // custom
    public final QAddress mainAddress = new QAddress(forProperty("mainAddress"));

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.friend = inits.isInitialized("friend") ? new QUser(forProperty("friend"), inits.get("friend")) : null;
    }

}

