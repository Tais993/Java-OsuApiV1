package nl.tijsbeek.api.cache;

import nl.tijsbeek.api.entities.NameHolder;

public interface NameCache<T extends NameHolder> extends Iterable<T> {

    T getItemByName(String name);
}
