package nl.tijsbeek.api.cache;

import nl.tijsbeek.api.entities.NameHolder;

public interface NameCache<T extends NameHolder> {

    T getItemByName(String name);
}
