package com.technologygarden.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class dfs {
    public static void main(String[] args) throws IOException {
        File directory = new File("");// 参数为空
        int b=0;
        Integer a=b;
        String path = directory.getCanonicalPath()+"\\upload";
        System.out.println(new Date("yyyy-MM-dd HH:mm:ss"));
    }
}
