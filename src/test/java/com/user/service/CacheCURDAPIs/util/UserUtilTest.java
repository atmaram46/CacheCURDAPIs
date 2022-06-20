package com.user.service.CacheCURDAPIs.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserUtilTest {

    @InjectMocks
    private UserUtil userUtil;

    @Test
    public void isValidTest() {
        Assert.assertTrue(userUtil.isValid("20200101"));
    }

    @Test
    public void isValidFalseTest() {
        Assert.assertFalse(userUtil.isValid("20230101"));
    }

    @Test
    public void isValidFalse2Test() {
        Assert.assertFalse(userUtil.isValid("01012020"));
    }
}
