package io.sandstorm.controller.portadapter.persist.mgo;

import io.sandstorm.common.domain.model.EntityId;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

@Deprecated
public class EntityIdCodec implements Codec<EntityId> {
    @Override
    public void encode(final BsonWriter writer, final EntityId value, final EncoderContext encoderContext) {
        writer.writeObjectId(new ObjectId(value.get()));
    }

    @Override
    public EntityId decode(final BsonReader reader, final DecoderContext decoderContext) {
        ObjectId objectId = reader.readObjectId();
        return new EntityId(objectId.toHexString());
    }

    @Override
    public Class<EntityId> getEncoderClass() {
        return EntityId.class;
    }
}
