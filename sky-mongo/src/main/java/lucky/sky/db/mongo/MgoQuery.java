package lucky.sky.db.mongo;

import lucky.sky.db.mongo.convert.DateConverter;
import lucky.sky.db.mongo.data.PageInfoSupport;
import lucky.sky.db.mongo.data.PageResult;
import lucky.sky.db.mongo.lang.EnumValueSupport;
import lucky.sky.db.mongo.lang.Enums;
import lucky.sky.db.mongo.lang.Numbers;
import lucky.sky.db.mongo.lang.StrKit;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class MgoQuery<T> {

    private static final Logger log = LoggerFactory.getLogger(MgoQuery.class);
    private static final Logger qdump = LoggerFactory.getLogger("lucky.sky.db.mongo.query");

    private Query<T> query;

    public MgoQuery(Query<T> query) {
        this.query = query;
    }

    /**
     * 底层 Query~T 对象。
     */
    public Query<T> query() {
        return query;
    }

    /**
     * 假如 value 为 null 或 0 或空字符串，则忽略此条件。
     */
    public MgoQuery<T> filterIf(String condition, Object value) {
        filterIf(query, condition, value);
        return this;
    }

    /**
     * 假如 value.equals(ingoreValue) 则忽略此条件。
     */
    public MgoQuery<T> filterIf(String condition, Object value, Object ignoreValue) {
        filterIf(query, condition, value, ignoreValue);
        return this;
    }

    /**
     * field >= begin && field < end，假如 begin/end 为 null，则忽略此条件
     *
     * @param begin 闭区间，即 >= begin
     * @param end   开区间，即 < end
     */
    public MgoQuery<T> btw(String field, LocalDateTime begin, LocalDateTime end) {
        btw(query, field, begin, end);
        return this;
    }

    /**
     * 使用 unix 时间戳构建时间区间查询条件，即 field >= begin && field < end，假如 begin/end <= 0，则忽略此条件
     *
     * @param begin 闭区间，即 >= begin
     * @param end   开区间，即 < end
     */
    public MgoQuery<T> btwUnixEpoch(String field, long begin, long end) {
        btwUnixEpoch(query, field, begin, end);
        return this;
    }

    public MgoQuery<T> in(String field, Iterable<?> value) {
        in(query, field, value);
        return this;
    }

    /**
     * 如果 value == 0 或者找不到 value 对应的枚举定义 则忽略
     */
    public <TEnum extends EnumValueSupport, Enum> MgoQuery<T> eq(String field, int value,
                                                                 Class<TEnum> clazz) {
        eq(query, field, value, clazz);
        return this;
    }

    /**
     * 设置分页
     */
    public MgoQuery<T> page(PageInfoSupport pageInfo) {
        page(query, pageInfo);
        return this;
    }

    public T get() {
        dump();
        return query.get();
    }

    public List<T> asList() {
        dump();
        return query.asList();
    }

    /**
     * 返回总数量，自动忽略 limit & offset 条件。
     */
    public long countAll() {
        dump();
        return query.countAll();
    }

    private void dump() {
        qdump.debug("mgo query: {}", toString());
    }

    /**
     * 返回分页结果集以及总记录数
     */
    public PageResult<T> asPageResult() {

        return asPageResult(query);
    }

    /**
     * 返回以字符串表示的查询表达式。
     */
    @Override
    public String toString() {
        return query.toString();
    }

    /**
     * 假如 value 为 null 或 0，则忽略此条件。
     */
    public static <T> Query<T> filterIf(Query<T> query, String condition, Object value) {
        if (value == null || Numbers.equalsZero(value) || StrKit.isBlank(value)) {
            return query;
        }

        return query.filter(condition, value);
    }

    /**
     * 假如 value.equals(ingoreValue) 则忽略此条件。
     */
    public static <T> Query<T> filterIf(Query<T> query, String condition, Object value,
                                        Object ignoreValue) {
        if (ignoreValue == null) {
            return filterIf(query, condition, value);
        }
        Objects.requireNonNull(value, "arg value");
        if (!ignoreValue.equals(value)) {
            query.filter(condition, value);
        }
        return query;
    }

    /**
     * field >= begin && field < end，假如 begin/end 为 null，则忽略此条件
     *
     * @param begin 闭区间，即 >= begin
     * @param end   开区间，即 < end
     */
    public static <T> Query<T> btw(Query<T> query, String field, LocalDateTime begin,
                                   LocalDateTime end) {
        Query<T> q = query;
        if (begin != null) {
            q = q.field(field).greaterThanOrEq(begin);
        }
        if (end != null) {
            q = q.field(field).lessThan(end);
        }
        return q;
    }

    /**
     * 使用 unix 时间戳构建时间区间查询条件，即 field >= begin && field < end，假如 begin/end <= 0，则忽略此条件
     *
     * @param begin 闭区间，即 >= begin
     * @param end   开区间，即 < end
     */
    public static <T> Query<T> btwUnixEpoch(Query<T> query, String field, long begin, long end) {
        Query<T> q = query;
        if (begin > 0) {
            q = q.field(field).greaterThanOrEq(DateConverter.ofEpochMilli(begin));
        }
        if (end > 0) {
            q = q.field(field).lessThan(DateConverter.ofEpochMilli(end));
        }
        return q;
    }

    public static <T> Query<T> in(Query<T> query, String field, Iterable<?> value) {
        if (value != null && value.iterator().hasNext()) {
            return query.filter(field + " in", value);
        }
        return query;
    }

    /**
     * 如果 value == 0 或者找不到 value 对应的枚举定义 则忽略
     */
    public static <T, TEnum extends EnumValueSupport, Enum> Query<T> eq(Query<T> query, String field,
                                                                        int value, Class<TEnum> clazz) {
        if (value != 0) {
            TEnum enumEntry = Enums.valueOf(clazz, value, false);
            if (enumEntry != null) {
                return query.filter(field, enumEntry);
            } else {
                log.warn("undefined value {} for enum {}", value, clazz.getName());
            }
        }
        return query;
    }


    /**
     * 设置分页
     */
    public static <T> Query<T> page(Query<T> query, PageInfoSupport pageInfo) {
        if (pageInfo == null) {
            return query;
        }
        return query.offset(pageInfo.offset()).limit(pageInfo.getPageSize());
    }

    public static <T> PageResult<T> asPageResult(MgoQuery<T> query) {
        return asPageResult(query.query);
    }

    public static <T> PageResult<T> asPageResult(Query<T> query) {
        PageResult<T> result = new PageResult<>();
        result.setTotalCount((int) query.countAll());
        result.setItems(query.asList());
        return result;
    }
}
