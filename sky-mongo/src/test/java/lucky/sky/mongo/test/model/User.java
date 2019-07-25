package lucky.sky.mongo.test.model;

import lombok.Getter;
import lombok.Setter;
import lucky.sky.db.mongo.DbEntity;
import org.mongodb.morphia.annotations.Id;

import java.time.LocalDateTime;

/**
 * @Auther: chaoqiang.zhou
 * @Date: 2019/7/25 15:27
 * @Description:id是自动生成的
 */
@Getter
@Setter
public class User implements DbEntity<String> {


    @Id
    private String id;
    private String uname;
    private LocalDateTime createTime;


}
