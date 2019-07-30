package lucky.sky.db.mongo.config;

import lucky.sky.db.mongo.lang.StrKit;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class ConfigParser {

    /**
     * 解析 xml 配置文件，如果有动态属性（格式 ${propertyname}) 则进行替换
     */
    public static Document resolveXml(String path) {
        String xml = resolveText(path);
        Document doc = XmlHelper.loadXml(xml);
        return doc;
    }


    /**
     * 对纯文本文件中 ${propertyname} 进行替换
     */
    public static String resolveText(String path) {
        StringBuilder sb = new StringBuilder();

        try (InputStream stream = new FileInputStream(path)) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(stream, StandardCharsets.UTF_8));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }

                //动态属性替换
//                line = resolvePropertiesCore(line);
                sb.append(line);
                sb.append(StrKit.newLine);
            }
        } catch (Exception ex) {
            throw new ConfigException(ex);
        }

        return sb.toString();
    }

//
//    private static String resolvePropertiesCore(String source) {
//        Pattern pattern = Pattern.compile("\\$\\{(?<prop>[^\\s\\{\\}]+)\\}");
//        Matcher matcher = pattern.matcher(source);
//        List<String> props = new ArrayList<>();
//        while (matcher.find()) {
//            String prop = matcher.group("prop");
//            props.add(prop);
//        }
//
//        String resolvedSource = source;
//        for (String p : props) {
//            String v = MongoDataBaseProperties.getPropertiesValue(p);
//            if (v != null) {
//                String placeHolder = String.format("${%s}", p);
//                resolvedSource = resolvedSource.replace(placeHolder, v);
//            }
//        }
//        return resolvedSource;
//    }


}
