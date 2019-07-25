package lucky.sky.mongo.test.dao;

import lucky.sky.mongo.test.model.Group;
import lucky.sky.mongo.test.model.Sex;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @Auther: chaoqiang.zhou
 * @Date: 2019/7/25 15:31
 * @Description:
 */
public class GroupDaoTest {

    GroupDao groupDao = new GroupDao();


    @Test
    public void insertUser() {

        Group group = new Group();
        group.setCreateTime(LocalDateTime.now());
        //auto自增的话，指定就不起作用
//        group.setId(12345);
        group.setUname("李四");
        Sex sex = Sex.WOMAN;
        group.setSex(sex);

        groupDao.addUsers(group);
    }


    @Test
    public void getGroup() {

        System.out.println(groupDao.getGroup(7).getSex().displayName());
    }


}
