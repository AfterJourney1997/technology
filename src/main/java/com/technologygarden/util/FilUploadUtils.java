package com.technologygarden.util;


import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

public class FilUploadUtils {

    public static Boolean saveFile(MultipartFile mfile) throws IOException {
        File directory = new File("");// 参数为空
        String path = directory.getCanonicalPath()+"\\upload";
        if(mfile.isEmpty()){
            return false;
        }

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = mfile.getOriginalFilename();
        mfile.transferTo(new File(file, filename));
        return true;
    }
    public static String getFilePath() throws IOException {
        File directory = new File("");// 参数为空
        String path = directory.getCanonicalPath()+"\\upload";
        return path;
    }
}
