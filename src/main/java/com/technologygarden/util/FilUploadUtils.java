package com.technologygarden.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@Slf4j
public class FilUploadUtils {

    @Value("${file.storagePath}")
    private static String fileStoagePath;

    @Value("${file.url}")
    private static String fileUrlHead;

/*    static {
        File filePath = new File(fileStoagePath);
        if(!filePath.exists()){
            filePath.mkdirs();
            log.info(fileStoagePath + " 路径不存在，已创建。");
        }
    }*/


    public static String saveFile(MultipartFile mfile) throws IOException {
        String path = fileStoagePath;
        if(mfile.isEmpty()){
            return "false";
        }

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");
        String fileUUName = uuidStr+mfile.getOriginalFilename().replace("/","");
        mfile.transferTo(new File(file, fileUUName));
        return fileUUName;
    }
    /*
    获取图片显示路经
    */
    public static String getImageShowPath() {
        return fileUrlHead;
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

    //filename是从数据库中查出的UUFileName
    public static ResponseEntity<byte[]> downloadFile(String filename) throws IOException {
        log.info("接受的参数："+filename);
        HttpHeaders headers = new HttpHeaders();
//        File isFile=new File(filename);
        //如果是绝对路径则，需要剪出filename
//        if(isFile.exists()){
//            String name=shearPath(filename);
//            System.out.println("filename："+name);
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            //filename设置utf-8编码格式，中文会重新编码成一串字符串 %E6%B5%8B
//            name = URLEncoder.encode(name,"utf-8");
//            headers.setContentDispositionFormData("attachment",name);
//            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(isFile),
//                    headers, HttpStatus.OK);
//        }else{
//            File directory = new File("");// 参数为空
        String path = fileStoagePath;
        //因为filename是UUName，所以调用getfileName()方法获得真正的filename名字
        String nameReal = getfileName(filename);
        File file = new File(path+filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //filename设置utf-8编码格式，中文会重新编码成一串字符串 %E6%B5%8B
        nameReal = URLEncoder.encode(nameReal,"utf-8");
        headers.setContentDispositionFormData("attachment",nameReal);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.OK);
    }
//    public static String shearPath(String absolutePath) throws IOException {
//        File directory = new File("");// 参数为空
//        String path = directory.getCanonicalPath()+"\\upload";
//        Integer extraengthL=path.length()+33;
//        StringBuffer sb = new StringBuffer();
//        for (int i =extraengthL; i < absolutePath.length(); i++) {
//            sb.append(absolutePath.charAt(i));
//        }
//        return sb.toString();
//    }





    public static boolean deleteFile(String UUName) throws IOException {
        String filePath=fileStoagePath+UUName;
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            Boolean succeedDelete = file.delete();
            if (succeedDelete) {
                log.info("删除单个文件" + UUName + "成功！");
                return true;
            } else {
                log.info("删除单个文件" + UUName + "失败！");
                return true;
            }
        } else {
            log.info("删除单个文件" + UUName + "失败！");
            return false;
        }

    }

    public static byte[] imageToByte(File image, String type) throws Exception {

        byte[] bytes = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(image);
            ImageIO.write(bufferedImage, type, byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return bytes;
    }

/*    // 根据单个imageName获取访问url
    public static String getImageUrl(String imageFileName){

        String imagePath = fileStoagePath + imageFileName;
        File image = new File(imagePath);

        if(!image.exists() || !image.isFile()){
            log.warn("该图片不存在或非文件");
            return null;
        }

        return fileUrlHead + imageFileName;

    }

    // 根据imageName数组获取访问url数组
    public static String[] getImageUrlArray(String[] imageFileNames){

        for(int i = 0; i < imageFileNames.length; i++){

            String imagePath = fileStoagePath + imageFileNames[i];
            File image = new File(imagePath);

            if(!image.exists() || !image.isFile()){
                log.warn(imageFileNames[i] + "：该图片不存在或非文件");
                return null;
            }
            imageFileNames[i] = fileUrlHead + imageFileNames[i];
        }

        return imageFileNames;

    }*/
}
