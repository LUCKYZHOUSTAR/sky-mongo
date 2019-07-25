package lucky.sky.db.mongo.lang;

/**
 * 数字相关实用工具。
 */
public class Numbers {

    public static boolean equalsZero(Object num) {
        if (num instanceof Number) {
            double d = ((Number) num).doubleValue();
            return d >= 0 && d <= Double.MIN_VALUE;
        }
        return false;
    }
}
