package com.kelab.util.ymlparse;

import com.kelab.util.ymlparse.annotation.Yaml;

import java.lang.reflect.Field;

public class DataSetter {
    public static void setData(String yml, Object appSetting) throws Exception {
        Class<?> clazz = appSetting.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field single : fields) {
            if (single.isAnnotationPresent(Yaml.class)) {
                Yaml yaml = single.getAnnotation(Yaml.class);
                if (!single.isAccessible()) {
                    single.setAccessible(true);
                }
                String value = YmlUtil.getValue(yml, yaml.value()).toString();
                if (Integer.class.equals(single.getType())) {
                    single.set(appSetting, Integer.valueOf(value));
                } else if (String.class.equals(single.getType())) {
                    single.set(appSetting, value);
                } else if (Long.class.equals(single.getType())) {
                    single.set(appSetting, Long.valueOf(value));
                } else if (Boolean.class.equals(single.getType())) {
                    single.set(appSetting, Boolean.valueOf(value));
                } else if (Double.class.equals(single.getType())) {
                    single.set(appSetting, Double.valueOf(value));
                }
            }
        }
    }
}
