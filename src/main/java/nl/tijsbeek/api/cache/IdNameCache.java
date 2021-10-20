package nl.tijsbeek.api.cache;

import nl.tijsbeek.api.entities.IdHolder;
import nl.tijsbeek.api.entities.NameHolder;

public interface IdNameCache<T extends IdHolder & NameHolder>
        extends IdCache<T>, NameCache<T>, Iterable<T> {

}
