package com.technologygarden.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Component
public class FilUploadUtils {

    private static String FILE_STORAGE_PATH;
    private static String FILE_URL_HEAD;
    private static final int BUFFER_SIZE = 2*1024*1024;//定义2M缓冲区

    @Autowired
    public FilUploadUtils(@Value("${file.storagePath}") String FILE_STORAGE_PATH, @Value("${file.url}") String FILE_URL_HEAD) {
        FilUploadUtils.FILE_STORAGE_PATH = FILE_STORAGE_PATH;
        FilUploadUtils.FILE_URL_HEAD = FILE_URL_HEAD;
    }

    public static String saveFile(MultipartFile mfile) throws IOException {

        String path = FILE_STORAGE_PATH;
        if (mfile.isEmpty()) {
            return "false";
        }

        File file = new File(FILE_STORAGE_PATH);
        if (!file.exists()) {
            log.info(FILE_STORAGE_PATH + " 路径不存在，已创建。");
            file.mkdirs();
        }

        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");
        String fileUUName = uuidStr + mfile.getOriginalFilename().replace("/", "");
        mfile.transferTo(new File(file, fileUUName));
        return fileUUName;
    }

    /*
    获取文件存放路经
    */
    public static String getImageShowPath() {
        return FILE_URL_HEAD;
    }
    /*
    获取图片显示路经
    */
    public static String getFileStoragePath() {
        return FILE_STORAGE_PATH;
    }

    /*
     * 去除UUID，获得fileName
     * */
    public static String getfileName(String UUName) {
        if (UUName.length() <= 32) {
            return UUName;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 32; i < UUName.length(); i++) {
            sb.append(UUName.charAt(i));
        }
        return sb.toString();
    }

    //filename是从数据库中查出的UUFileName
    public static ResponseEntity<byte[]> downloadFile(String filename) throws IOException {
        log.info("接受的参数：" + filename);
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
        String path = FILE_STORAGE_PATH;
        //因为filename是UUName，所以调用getfileName()方法获得真正的filename名字
        String nameReal = getfileName(filename);
        File file = new File(path + filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //filename设置utf-8编码格式，中文会重新编码成一串字符串 %E6%B5%8B
        nameReal = URLEncoder.encode(nameReal, "utf-8");
        headers.setContentDispositionFormData("attachment", nameReal);
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
        String filePath = FILE_STORAGE_PATH + UUName;
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
            log.info("删除单个文件" + UUName + "失败！文件不存在");
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
    //压缩文件
    public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;

        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];//定义2M缓冲区
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

/*    // 根据单个imageName获取访问url
    public static String getImageUrl(String imageFileName){

        String imagePath = FILE_STORAGE_PATH + imageFileName;
        File image = new File(imagePath);

        if(!image.exists() || !image.isFile()){
            log.warn("该图片不存在或非文件");
            return null;
        }

        return FILE_URL_HEAD + imageFileName;

    }

    // 根据imageName数组获取访问url数组
    public static String[] getImageUrlArray(String[] imageFileNames){

        for(int i = 0; i < imageFileNames.length; i++){

            String imagePath = FILE_STORAGE_PATH + imageFileNames[i];
            File image = new File(imagePath);

            if(!image.exists() || !image.isFile()){
                log.warn(imageFileNames[i] + "：该图片不存在或非文件");
                return null;
            }
            imageFileNames[i] = FILE_URL_HEAD + imageFileNames[i];
        }

        return imageFileNames;

    }*/
}
