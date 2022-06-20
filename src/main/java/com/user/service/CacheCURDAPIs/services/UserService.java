package com.user.service.CacheCURDAPIs.services;

import com.user.service.CacheCURDAPIs.cache.CreateCacheMap;
import com.user.service.CacheCURDAPIs.entities.User;
import com.user.service.CacheCURDAPIs.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private CreateCacheMap createCacheMap;

    public String saveAndValidate(User user) {
        if(!UserUtil.isValid(user.getDob())) {
            return "Error in Date of Birth!!!";
        }
        if(createCacheMap.isKeyPresent(user.getEmail())) {
            return "Email already present!!!";
        }
        saveValidData(user);
        return "Success";
    }

    public String validateAndUpdate(User user) {
        if(!UserUtil.isValid(user.getDob())) {
            return "Error in Date of Birth!!!";
        }
        if(!createCacheMap.isKeyPresent(user.getEmail())) {
            System.out.println("Email not present!!! Adding new Entry!!!");
        }
        saveValidData(user);
        return "Success";
    }

    public User getDataForEmail(String email) {
        return createCacheMap.get(email);
    }

    private void saveValidData(User user) {
        createCacheMap.put(user.getEmail(), user);
    }

    public boolean removeKeyData(String email) {
        return createCacheMap.remove(email);
    }

}
