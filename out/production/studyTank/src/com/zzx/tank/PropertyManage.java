package com.zzx.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyManage {
    private static final PropertyManage INSTANCE = new PropertyManage();
    private Properties props = new Properties();

    private PropertyManage(){
        try {
            props.load(PropertyManage.class.getClassLoader().getResourceAsStream("property/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyManage getInstance(){
        return INSTANCE;
    }

    public Object getValue(String key){
        return props.get(key);
    }
}
