package nl.tijsbeek.api.cache;

import nl.tijsbeek.api.entities.User;

public interface CacheHandler {

    IdNameCache<User> getUserCache();
}
