package lucky.sky.mongo.test.model;

import lucky.sky.db.mongo.lang.EnumDisplayNameSupport;
import lucky.sky.db.mongo.lang.EnumValueSupport;

/**
 * @Auther: chaoqiang.zhou
 * @Date: 2019/7/25 16:13
 * @Description:
 */
public enum Sex implements EnumDisplayNameSupport, EnumValueSupport {


    MAN("男", 1),
    WOMAN("女", 2);

    private String displayName;

    private int value;


    Sex(String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

    @Override
    public String displayName() {
        return this.displayName;
    }

    @Override
    public int value() {
        return this.value;
    }
}
