package com.technologygarden.util;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public static ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request) throws IOException {

        File directory = new File("");// 参数为空
        String storagePath = directory.getCanonicalPath() + "\\upload";

        path = Paths.get(storagePath).toAbsolutePath().normalize();

        Path filePath = path.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }
}
