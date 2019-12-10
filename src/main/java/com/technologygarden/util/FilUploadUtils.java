package com.technologygarden.util;


import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

public class FilUploadUtils {
    public static void saveFile(MultipartFile Mfile) throws IOException {
        File directory = new File("");// 参数为空
        String path = directory.getCanonicalPath()+"\\upload";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = Mfile.getOriginalFilename();
        Mfile.transferTo(new File(file, filename));
    }
}
