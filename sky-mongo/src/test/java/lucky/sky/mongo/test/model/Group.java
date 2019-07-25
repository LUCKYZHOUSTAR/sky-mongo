package lucky.sky.mongo.test.model;

import lombok.Getter;
import lombok.Setter;
import lucky.sky.db.mongo.AutoIncrementId;
import lucky.sky.db.mongo.DbEntity;
import org.mongodb.morphia.annotations.Id;

import java.time.LocalDateTime;

/**
 * @Auther: chaoqiang.zhou
 * @Date: 2019/7/25 15:27
 * @Description:id是按照步长1来自动自增的
 */
@Getter
@Setter
@AutoIncrementId
public class Group implements DbEntity<Integer> {


    @Id
    private Integer id;
    private String uname;
    private LocalDateTime createTime;

    private Sex sex;


}
