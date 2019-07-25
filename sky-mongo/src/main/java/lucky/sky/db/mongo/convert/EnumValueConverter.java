package lucky.sky.db.mongo.convert;

import com.mongodb.DBObject;
import lucky.sky.db.mongo.lang.EnumValueSupport;
import lucky.sky.db.mongo.lang.Enums;
import org.apache.commons.lang3.ClassUtils;
import org.mongodb.morphia.converters.ConverterException;
import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.logging.Logger;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.mongodb.morphia.mapping.MappedField;

/**
 * Converts from/to strong-type Enum/BsonInt32 based {}@link EnumValueSupport.value()}
 */
@SuppressWarnings("unchecked")
public final class EnumValueConverter extends TypeConverter implements SimpleValueConverter {

    private static final Logger log = MorphiaLoggerFactory.get(EnumValueConverter.class);

    @Override
    protected boolean isSupported(Class<?> c, MappedField optionalExtraInfo) {
        return (c.isEnum() && ClassUtils.isAssignable(c, EnumValueSupport.class));
    }

    /**
     * decode the {@link DBObject} and provide the corresponding java (type-safe) object <br><b>NOTE:
     * optionalExtraInfo might be null</b>
     */
    @Override
    public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
        if (fromDBObject == null) {
            return null;
        }

        if (fromDBObject instanceof EnumValueSupport) {
            return fromDBObject;
        }

        Class<EnumValueSupport> enumClazz = (Class<EnumValueSupport>) targetClass;

        if (fromDBObject instanceof Number) {
            return Enums.valueOf(enumClazz, ((Number) fromDBObject).intValue());
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

        EnumValueSupport val = (EnumValueSupport) value;
        return val.value(); // new BsonInt32(val.value());
    }
}
