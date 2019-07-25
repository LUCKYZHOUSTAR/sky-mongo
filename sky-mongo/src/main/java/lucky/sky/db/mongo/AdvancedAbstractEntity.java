package lucky.sky.db.mongo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdvancedAbstractEntity<K> extends AbstractEntity<K> {

    private int createUser;
    private LocalDateTime createTime;
    private int updateUser;
    private LocalDateTime updateTime;

    /**
     * 将 create time 设为当前时间
     */
    public void setCreateTime() {
        this.createTime = LocalDateTime.now();
    }

    /**
     * 将 update time 设为当前时间
     */
    public void setUpdateTime() {
        this.updateTime = LocalDateTime.now();
    }
}
