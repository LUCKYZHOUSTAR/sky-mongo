package lucky.sky.db.mongo.codecs;

import lucky.sky.db.mongo.convert.DateConverter;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.time.LocalDateTime;

/**
 * Encodes and decodes the Java 8 LocalDateTime object.
 */
public class LocalDateTimeCodec implements Codec<LocalDateTime> {

    @Override
    public LocalDateTime decode(BsonReader bsonReader, DecoderContext decoderContext) {
        return DateConverter.ofEpochMilli(bsonReader.readDateTime());
    }

    @Override
    public void encode(BsonWriter bsonWriter, LocalDateTime localDateTime,
                       EncoderContext encoderContext) {
        bsonWriter.writeDateTime(DateConverter.toEpochMilli(localDateTime));
    }

    @Override
    public Class<LocalDateTime> getEncoderClass() {
        return LocalDateTime.class;
    }
}
