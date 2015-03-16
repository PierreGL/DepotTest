package org.pgl.mowerauto.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class manages internationalization.
 * */
public class Bundle {
    
    private static Bundle instance;
    
    private ResourceBundle resourceBundle;
    
    private Bundle(){
        Locale locale;
        
        //First check if the locale has been explicitly declared in the config file.
        String localeCode = Config.getInstance().get("LOCALE");

        if(localeCode != null){
            locale = new Locale(localeCode);
        }else{
            locale = Locale.getDefault();
            Locale.setDefault(locale);
        }

        resourceBundle = ResourceBundle.getBundle("ResourceBundle", locale);
    }
    
    public static synchronized Bundle getInstance(){
        if(instance == null){
            instance = new Bundle();
        }
        return instance;
    }
    
    /**
     * Provides the value matching key and loaded locale.
     * 
     * @param key Defined key.
     * @return Value.
     * */
    public String get(String key){
        return resourceBundle.getString(key);
    }

}
