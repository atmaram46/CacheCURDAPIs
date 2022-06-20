package com.user.service.CacheCURDAPIs.service;

import com.user.service.CacheCURDAPIs.cache.CreateCacheMap;
import com.user.service.CacheCURDAPIs.entities.User;
import com.user.service.CacheCURDAPIs.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private CreateCacheMap createCacheMap;

    @Before
    public void setUp() {
        User user = new User("arun", "kumar","email@gmail.com", "20010801");
        when(createCacheMap.get(anyString())).thenReturn(user);
        when(createCacheMap.remove(anyString())).thenReturn(true);
    }

    @Test
    public void addDataTest() {
        User user = new User("arun", "kumar","email@gmail.com", "20010801");
        userService.saveAndValidate(user);
        Mockito.verify(createCacheMap, Mockito.times(1)).put("email@gmail.com", user);
    }

    @Test
    public void getDataForKeyTest() {
        Assert.assertNotNull(userService.getDataForEmail("email@gmail.com"));
    }

    @Test
    public void getMapSize() {
        Assert.assertTrue(userService.removeKeyData("email@gmail.com"));
    }

    @Test
    public void updateUserDataTest() {
        User user = new User("arun", "kumar","email@gmail.com", "20010801");
        userService.validateAndUpdate(user);
        Mockito.verify(createCacheMap, Mockito.times(1)).put("email@gmail.com", user);
    }
}
