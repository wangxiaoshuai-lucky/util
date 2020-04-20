package com.kelab.util;

import com.kelab.util.verifycode.VerifyCodeUtils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        VerifyCodeUtils.ImgResult imgResult = VerifyCodeUtils.VerifyCode(100, 35, 4);
        System.out.println(imgResult.getImg());
    }
}
