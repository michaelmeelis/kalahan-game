package app.datastore;

import java.util.UUID;

public interface DatastoreInterface {
    Object findByUuid(String objectName, UUID uuid);
    void persist(UUID uuid, Object model);
}
