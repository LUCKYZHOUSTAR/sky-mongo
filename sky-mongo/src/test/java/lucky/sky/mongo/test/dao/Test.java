//package com.ehomepay.web.mongo.dao;
//
//import com.ehomepay.web.mongo.model.AsssimentInfo;
//import com.ehomepay.web.mongo.model.UserInfo;
//import lucky.sky.db.mongo.MgoDao;
//import lucky.sky.db.mongo.MgoUpdater;
//import org.bson.types.ObjectId;
//import org.mongodb.morphia.Key;
//import org.mongodb.morphia.query.Query;
//import org.mongodb.morphia.query.UpdateResults;
//
//import java.time.LocalDateTime;
//
///**
// * @Auther: chaoqiang.zhou
// * @Date: 2019/7/30 11:29
// * @Description:
// */
//public class UserinfoDao extends MgoDao<UserInfo, String> {
//
//    public UserinfoDao() {
//        super("sky_read");
//    }
//
//
//    public void saveUserInfo(UserInfo userInfo) {
//
//        Key<UserInfo> id = save(userInfo);
//
//        System.out.println(id.getId());
//
//        System.out.println(id.getCollection());
//
//
//    }
//
//    public UserInfo user(ObjectId id) {
//        return createMgoQuery().filterIf("_id", id).get();
//    }
//
//
//    public void query(String id, String uid) {
//
//
//        Query<UserInfo> query = createQuery().field("_id").equal(new ObjectId(id)).field("asssimentInfos._id").equal("234234234");
//
////        MgoUpdater updater = createUpdater().set("asssimentInfos.$.uname", "中国");
////
//
//        AsssimentInfo asssimentInfo = new AsssimentInfo();
//        asssimentInfo.setId("234234223434");
//        asssimentInfo.setCreateTime(LocalDateTime.now());
//
//        asssimentInfo.setUname("美国");
//        MgoUpdater updater = createUpdater();
//
//        //动态添加字段
//        updater.add("asssimentInfos", asssimentInfo);
//        //对某个字段增加100
////        updater.inc("", 100);
//        //向数组添加一个数据
//        UpdateResults results = update(query, updater);
//        UserInfo userInfo = query.get();
//
//
//        System.out.println("");
//
//
//        Query<UserInfo> query2 = createQuery().field("_id").endsWith("34");
//
//        UserInfo userInfo2 = query.get();
//
//        System.out.println("");
//
//
//    }
//
//
//    public void query() {
//
//    }
//
//
//    public UserInfo getUserInfo(String id) {
//        return get(id);
//    }
//
//    public static void main(String[] args) {
//        UserinfoDao userinfoDao = new UserinfoDao();
//
//
//        userinfoDao.query("5d3fc0ae65329f064a33f6c9", "234");
//
//        UserInfo userInfo1 = userinfoDao.user(new ObjectId("5d3fc0ae65329f064a33f6c9"));
//
////
//        UserInfo userInfo = new UserInfo();
////
////        AsssimentInfo asssimentInfo = new AsssimentInfo();
////        asssimentInfo.setId("234234234");
////        asssimentInfo.setCreateTime(LocalDateTime.now());
////
////        asssimentInfo.setUname("王刚");
////
////        List ass = new ArrayList<>();
////        ass.add(asssimentInfo);
////
////
////        userInfo.setAsssimentInfos(ass);
////        userInfo.setUname("说了");
////
////
////        userinfoDao.saveUserInfo(userInfo);
//
//
//    }
//}
