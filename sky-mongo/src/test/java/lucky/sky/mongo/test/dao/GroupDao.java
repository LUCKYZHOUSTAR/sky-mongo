package lucky.sky.mongo.test.dao;

import lucky.sky.db.mongo.MgoDao;
import lucky.sky.mongo.test.model.Group;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

/**
 * @Auther: chaoqiang.zhou
 * @Date: 2019/7/25 15:29
 * @Description:
 */
public class GroupDao extends MgoDao<Group, Integer> {

    public GroupDao() {
        super("sky_read");
    }


    public void addUsers(Group user) {

        //save 方法对于已经存在的 key 将进行 update，而 insert 方法对于已经存在的 key 将抛出 DuplicateKeyException 异常。
        insertEntities(user);
        save(user);
    }


    public Group getGroup(Integer id) {

        return get(id);
    }


    public void UpdateGroup(Integer id) {
        UpdateOperations updateOperations = createUpdateOperations().set("pwd", "sdf");

        UpdateResults updateResults = updateById(id, updateOperations);
    }


}
