package com.rixon.lms.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author: Rixon Mathew(rixonmathew@gmail.com)
 * date   : 9/1/11 - 2:30 PM
 */
public class LMSResourceBundle {

    public static String getString(Class<?> baseName, String key) {
        return ResourceBundle.getBundle(baseName.getSimpleName(), Locale.getDefault()).getString(key);
    }
}


