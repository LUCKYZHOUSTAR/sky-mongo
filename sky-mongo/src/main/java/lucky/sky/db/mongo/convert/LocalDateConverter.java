package lucky.sky.db.mongo.convert;

import org.bson.BsonDateTime;
import org.mongodb.morphia.converters.ConverterException;
import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.logging.Logger;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.mongodb.morphia.mapping.MappedField;

import java.time.LocalDate;
import java.util.Date;


/**
 * Converts from/to Java 8 LocalDate/BsonDate. BsonDate 的時間部分用 00:00:00.000 表示。
 */
public final class LocalDateConverter extends TypeConverter implements SimpleValueConverter {

    private static final Logger log = MorphiaLoggerFactory.get(LocalDateConverter.class);

    public LocalDateConverter() {
        super(LocalDate.class);
    }

    /**
     * optionalExtraInfo might be null</b>
     */
    @Override
    public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
        if (fromDBObject == null) {
            return null;
        }

        if (fromDBObject instanceof LocalDate) {
            return fromDBObject;
        }

        if (fromDBObject instanceof Number) {
            return DateConverter.ofEpochMilli(((Number) fromDBObject).longValue()).toLocalDate();
        }

        if (fromDBObject instanceof Date) {
            return DateConverter.toLocalDate((Date) fromDBObject);
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

        LocalDate dt = (LocalDate) value;
        return new BsonDateTime(DateConverter.toEpochMilli(dt));
    }
}
