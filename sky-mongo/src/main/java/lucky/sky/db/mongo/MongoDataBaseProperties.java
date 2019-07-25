package lucky.sky.db.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringValueResolver;

/**
 * @Auther: chaoqiang.zhou
 * @Date: 2019/7/25 17:02
 * @Description:
 */
@Configuration
@EnableConfigurationProperties
public class MongoDataBaseProperties implements ApplicationContextAware, EmbeddedValueResolverAware {

    private static Logger logger = LoggerFactory.getLogger(MongoDataBaseProperties.class);


    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    private static StringValueResolver stringValueResolver;

    /**
     * 实现ApplicationContextAware接口的回调方法。设置上下文环境
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        MongoDataBaseProperties.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    /**
     * 动态获取配置文件中的值
     *
     * @param name
     * @return
     */
    public static String getPropertiesValue(String name) {
        try {
            name = "${" + name + "}";
            return MongoDataBaseProperties.stringValueResolver.resolveStringValue(name);
        } catch (Exception e) {
            logger.error(String.format("当前环境变量中没有{%s}的配置", name));
            // 获取失败则返回null
            return null;
        }
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        MongoDataBaseProperties.stringValueResolver = stringValueResolver;
    }

}
