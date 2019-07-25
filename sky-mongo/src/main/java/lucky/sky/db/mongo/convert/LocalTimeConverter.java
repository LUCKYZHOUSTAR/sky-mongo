package lucky.sky.db.mongo.convert;

import org.bson.BsonDateTime;
import org.mongodb.morphia.converters.ConverterException;
import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.logging.Logger;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.mongodb.morphia.mapping.MappedField;

import java.time.LocalTime;
import java.util.Date;

/**
 * Converts from/to Java 8 LocalTime/BsonDate. BsonDate 的日期部分用 1970-1-1 表示。
 */
public final class LocalTimeConverter extends TypeConverter implements SimpleValueConverter {

    private static final Logger log = MorphiaLoggerFactory.get(LocalTimeConverter.class);

    public LocalTimeConverter() {
        super(LocalTime.class);
    }

    /**
     * optionalExtraInfo might be null</b>
     */
    @Override
    public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
        if (fromDBObject == null) {
            return null;
        }

        if (fromDBObject instanceof LocalTime) {
            return fromDBObject;
        }

        if (fromDBObject instanceof Number) {
            return DateConverter.ofEpochMilli(((Number) fromDBObject).longValue()).toLocalTime();
        }

        if (fromDBObject instanceof Date) {
            return DateConverter.toLocalDateTime((Date) fromDBObject).toLocalTime();
        }

        throw new ConverterException(String
                .format("Can't convert to LocalDateTime from %s@%s", fromDBObject.getClass(),
                        fromDBObject));
    }

    @Override
    public Object encode(Object value, MappedField optionalExtraInfo) {
        if (value == null) {
            return null;
        }

        LocalTime dt = (LocalTime) value;
        return new BsonDateTime(DateConverter.toEpochMilli(dt.atDate(DateConverter.EPOCH_DAY)));
    }
}
