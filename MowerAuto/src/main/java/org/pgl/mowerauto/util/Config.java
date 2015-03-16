package org.pgl.mowerauto.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class manages loading and access configuration file.
 * */
public class Config {
    
    private static final Logger LOGGER = LogManager.getLogger(Config.class);
    
    private static Config instance;
    
    private Properties prop;
    
    private Config(){
        this.prop = new Properties();
        InputStream is = getClass().getResourceAsStream("/config.properties");
        try {
            this.prop.load(is);
        } catch (IOException ie) {
            LOGGER.error(ie);
        }
    }
    
    public static Config getInstance(){
        if(instance == null){
            instance = new Config();
        }
        return instance;
    }
    
    /**
     * Provides value matching the defined key in config file.
     * 
     * @param key Defined key.
     * @return The value.
     * */
    public String get(String key){
        return this.prop.getProperty(key);
    }

}
