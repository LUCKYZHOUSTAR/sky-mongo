package lucky.sky.db.mongo;

import lombok.Getter;
import lombok.Setter;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 */
@Getter
@Setter
@Entity(noClassnameStored = true)
public abstract class AbstractEntity<K> implements DbEntity<K> {

    @Id
    private K id;
}
