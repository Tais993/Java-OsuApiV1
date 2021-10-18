package nl.tijsbeek.api.cache;

import nl.tijsbeek.api.entities.IdHolder;

public interface IdCache<T extends IdHolder> {

    T getItemById(long id);

    default T getItemById(String id) {
        return getItemById(Long.parseLong(id));
    }
}
