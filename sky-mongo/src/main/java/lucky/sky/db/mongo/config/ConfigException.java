package lucky.sky.db.mongo.config;


public class ConfigException extends RuntimeException {

  public ConfigException() {
    super();
  }

  public ConfigException(String msg) {
    super(msg);
  }

  public ConfigException(Exception inner) {
    super(inner);
  }

  public ConfigException(String msg, Exception inner) {
    super(msg, inner);
  }
}
