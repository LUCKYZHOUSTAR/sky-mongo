package lucky.sky.mongo.test.dao;

import lucky.sky.mongo.test.model.User;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @Auther: chaoqiang.zhou
 * @Date: 2019/7/25 15:31
 * @Description:
 */
public class UserDaoTest {

    UserDao userDao = new UserDao();


    @Test
    public void insertUser() {

        User user = new User();
        user.setCreateTime(LocalDateTime.now());
//        user.setId("23425");
        user.setUname("李四");
        userDao.addUsers(user);
    }

    @Test
    public void queryUser() {
        User user = userDao.get("2342");
        System.out.println(user.getCreateTime());
    }


    @Test
    public void saveUser() {

        /**
         * id自动生成ObjectId的类型
         * id自动生成ObjectId的类型
         */
        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        user.setUname("张三");
        userDao.addUsers(user);
    }

    @Test
    public void queryUserByID() {
        User user = userDao.user(new ObjectId("5b458b60f49ac40523cb0cc2"));
        System.out.println(user.getCreateTime());
        System.out.println(user.getId());
    }


}
