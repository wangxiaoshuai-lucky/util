package com.kelab.util.uuid;

import java.util.UUID;

public class UuidUtil {
    public static String genUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
