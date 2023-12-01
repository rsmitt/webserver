package org.example.webserver.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User storage
 */
public class UserStorage {

    private final  Map<String, UserInfo>users = new HashMap<>();

    public Collection<UserInfo> getAll() {
        return users.values();
    }

    public UserInfo findUserById(String id) {
        return users.get(id);
    }

    public void removeById(String id) {
        users.remove(id);
    }

    public void add(UserInfo userInfo) {
        users.put(userInfo.getId(), userInfo);
    }
}
