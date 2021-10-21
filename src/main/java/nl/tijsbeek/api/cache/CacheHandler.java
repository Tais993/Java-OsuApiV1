package nl.tijsbeek.api.cache;

import nl.tijsbeek.api.entities.UserImpl;

public interface CacheHandler {

    IdNameCache<UserImpl> getUserCache();
}
