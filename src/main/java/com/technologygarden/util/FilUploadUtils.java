package com.technologygarden.util;


import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.UUID;

public class FilUploadUtils {

    private static Path path;

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

    //filename要么是绝对路径，要么是从数据库中查出的UUFileName
    public static ResponseEntity<byte[]> downloadFile(String filename, HttpServletRequest request) throws IOException {
        System.out.println("接受的参数："+filename);
        HttpHeaders headers = new HttpHeaders();
        File isFile=new File(filename);
        //如果是绝对路径则，需要剪出filename
        if(isFile.exists()){
            String name=shearPath(filename);
            System.out.println("filename："+name);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //filename设置utf-8编码格式，中文会重新编码成一串字符串 %E6%B5%8B
            name = URLEncoder.encode(name,"utf-8");
            headers.setContentDispositionFormData("attachment",name);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(isFile),
                    headers, HttpStatus.OK);
        }else{
            File directory = new File("");// 参数为空
            String path = directory.getCanonicalPath()+"\\upload";
            //因为filename是UUName，所以调用getfileName()方法获得真正的filename名字
            String nameReal = getfileName(filename);
            File file = new File(path+"/"+nameReal);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //filename设置utf-8编码格式，中文会重新编码成一串字符串 %E6%B5%8B
            nameReal = URLEncoder.encode(nameReal,"utf-8");
            System.out.println("参数path："+path);
            headers.setContentDispositionFormData("attachment",nameReal);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.OK);
        }


    }
    public static String shearPath(String absolutePath) throws IOException {
        File directory = new File("");// 参数为空
        String path = directory.getCanonicalPath()+"\\upload";
        Integer extraengthL=path.length()+33;
        StringBuffer sb = new StringBuffer();
        for (int i =extraengthL; i < absolutePath.length(); i++) {
            sb.append(absolutePath.charAt(i));
        }
        return sb.toString();
    }





    public static boolean deleteFile(String UUName) throws IOException {
        File directory = new File("");// 参数为空
        String filePath=UUName+directory.getCanonicalPath()+"\\upload";
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            Boolean succeedDelete = file.delete();
            if (succeedDelete) {
                System.out.println("删除单个文件" + UUName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + UUName + "失败！");
                return true;
            }
        } else {
            System.out.println("删除单个文件" + UUName + "失败！");
            return false;
        }

    }
}
