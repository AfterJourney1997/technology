package com.technologygarden.util;


import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FilUploadUtils {

    public static String saveFile(MultipartFile mfile) throws IOException {
        File directory = new File("");// 参数为空
        String path = directory.getCanonicalPath()+"\\upload";
        if(mfile.isEmpty()){
            return "false";
        }

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");
        String fileUUName = uuidStr+mfile.getOriginalFilename();
        mfile.transferTo(new File(file, fileUUName));
        return fileUUName;
    }
    /*
    获取文件存放路经
    */
    public static String getFilePath() throws IOException {
        File directory = new File("");// 参数为空
        String path = directory.getCanonicalPath()+"\\upload";
        return path;
    }
    /*
    * 去除UUID，获得fileName
    * */
    public static String getfileName(String UUName) {
        if(UUName.length()<=32){
            return UUName;
        }
        StringBuffer sb = new StringBuffer();
        for (int i =32; i < UUName.length(); i++) {
            sb.append(UUName.charAt(i));
        }
        return sb.toString();
    }
}
