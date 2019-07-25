package lucky.sky.db.mongo;

import lombok.Getter;
import lombok.Setter;
import lucky.sky.db.mongo.config.ConfigManager;
import lucky.sky.db.mongo.config.ConfigParser;
import lucky.sky.db.mongo.config.XmlHelper;
import lucky.sky.db.mongo.lang.SettingMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

/***
 * mongo config 配置信息
 */
public final class MongoConfig {

    private MongoConfig() {
    }

    private static HashMap<String, MongoInfo> infos = new HashMap<>();

    static {
        loadConfig();
    }

    private static void loadConfig() {
        String filePath = ConfigManager.findConfigPath("db.mongo", ".conf", ".xml");
        if (filePath == null) {
            return;
        }

        Document doc = ConfigParser.resolveXml(filePath);
        NodeList nodes = doc.getElementsByTagName("database");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element elem = (Element) nodes.item(i);

            MongoInfoImp info = new MongoInfoImp();
            NodeList settingNodes = elem.getElementsByTagName("setting");
            info.settings = XmlHelper.toSettingMap(settingNodes, "name", "value");
            info.name = elem.getAttribute("name");
            info.url = info.settings.getString("ConnString");
            if (!info.url.startsWith("mongodb://")) {
                info.url = "mongodb://" + info.url;
            }

            infos.put(info.name, info);
        }
    }

    public synchronized static MongoInfo get(String name) {
        MongoInfo info = infos.get(name);
        return info;
    }


    public interface MongoInfo {

        String getName();

        String getUrl();

        String getVersion();

    }

    @Getter
    @Setter
    static class MongoInfoImp implements MongoInfo {

        private String name;
        private String url;
        private String version;
        private Map<String, String> options;
        private SettingMap settings;

    }

}
