package com.kelab.util.ymlparse;

import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class YmlUtil {

    /**
     * key:文件名索引
     * value:配置文件内容
     */
    private static Map<String, LinkedHashMap<?,?>> data = new HashMap<String, LinkedHashMap<?,?>>();

    /**
     * string:当前线程需要查询的文件名
     */
    private static ThreadLocal<String> nowFileName = new ThreadLocal<String>();

    /**
     * 加载配置文件
     * @param fileName yml位置
     */
    public static void loadYml(String fileName) {
        nowFileName.set(fileName);
        if (!data.containsKey(fileName)) {
            data.put(fileName, new Yaml().loadAs(YmlUtil.class.getResourceAsStream("/" + fileName), LinkedHashMap.class));
        }
    }

    public static Object getValue(String key) throws Exception {
        // 首先将key进行拆分
        String[] keys = key.split("[.]");

        // 将配置文件进行复制
        Map<?,?> ymlInfo = (Map<?,?>) data.get(nowFileName.get()).clone();
        for (int i = 0; i < keys.length; i++) {
            Object value = ymlInfo.get(keys[i]);
            if (i < keys.length - 1) {
                ymlInfo = (Map<?,?>) value;
            } else if (value == null) {
                throw new Exception("key不存在");
            } else {
                return value;
            }
        }
        throw new RuntimeException("不可能到这里的...");
    }

    public static Object getValue(String fileName, String key) throws Exception {
        // 首先加载配置文件
        loadYml(fileName);
        return getValue(key);
    }
}
