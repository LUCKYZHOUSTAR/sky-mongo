package lucky.sky.db.mongo;

public interface DbEntity<K> {

    K getId();

    void setId(K id);
}
