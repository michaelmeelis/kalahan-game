package app.datastore;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class Datastore implements DatastoreInterface{
    private HashMap<UUID, Object> data;

    Datastore() {
        this.data = new HashMap<>();
    }

    public Object findByUuid(String objectName, UUID uuid) {
        return this.data.get(uuid);
    }

    public void persist(UUID uuid, Object model) {
        this.data.put(uuid, model);
    }
}
