package com.user.service.CacheCURDAPIs.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.util.Properties;

@Getter
@Configuration
public class PropConfig {

    public String getCacheSize1() {
        FileReader reader= null;
        Properties p=new Properties();
        try {
            reader = new FileReader("src/main/resources/application.properties");
            p.load(reader);
            reader.close();
        } catch (Exception e) {
            System.out.println("Error while Reading Properties file!!!");
            e.printStackTrace();
        }
        return p.getProperty("user.cache.size");
    }
}
