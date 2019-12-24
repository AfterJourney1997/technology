package com.technologygarden.controller;

import cn.hutool.core.io.FileTypeUtil;
import com.technologygarden.util.FilUploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@CrossOrigin
@Api(tags = "文件接口", value = "FileController")
public class FileController {

    @Value("${file.storagePath}")
    private String fileStoagePath;

    @RequestMapping(value = "/image/{image_name}", method = RequestMethod.GET)
    @ApiOperation(value = "获取图片", notes = "参数包括：图片名称（包括文件后缀）")
    public ResponseEntity<byte[]> getImage(@PathVariable("image_name") String image_name) throws Exception {

        byte[] imageContent;
        String path = fileStoagePath + image_name;
        File file = new File(path);

        // 判断图片是否存在
        if (!file.exists()) {
            log.warn(path + "：该图片不存在。");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 获取图片类型
        String type = FileTypeUtil.getType(file);
        imageContent = FilUploadUtils.imageToByte(file, type);

        final HttpHeaders headers = new HttpHeaders();
        if (type.equals("jpg") || type.equals("jpeg")) {
            headers.setContentType(MediaType.IMAGE_JPEG);
        } else if (type.equals("png")) {
            headers.setContentType(MediaType.IMAGE_PNG);
        }
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/downloadFile/{imageName}", method = RequestMethod.GET)
    @ApiOperation(value = "下载文件", notes = "参数包括：文件名称")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("imageName") String imageName) throws Exception {
        if (!StringUtils.isEmpty(imageName)) {
            return FilUploadUtils.downloadFile(imageName);
        }
        return null;
    }

    @RequestMapping(value = "/downZip", method = RequestMethod.POST)
    @ApiOperation(value = "打包下载文件", notes = "参数包括：文件名称集合")
    public ResponseEntity<byte[]> downZip(@RequestBody List<String> fileNameList) throws FileNotFoundException {
        List<File> fileList = new ArrayList<>();
        for (String fileName : fileNameList) {
            File file = new File(FilUploadUtils.getFileStoragePath() + fileName);
            //判断文件是否存在
            if (file.exists()) {
                fileList.add(file);
            }
        }
        //定义压缩文件名字
        String name = System.currentTimeMillis() + ".zip";
        //压缩文件存放地址
        String path = FilUploadUtils.getFileStoragePath() + name;
        File zipFile = new File(path);
        if (!zipFile.getParentFile().exists()) {
            zipFile.getParentFile().mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(zipFile);
        FilUploadUtils.toZip(fileList, fos);
        //下载压缩文件并删除压缩文件
        try {
            return FilUploadUtils.downloadFile(name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                FilUploadUtils.deleteFile(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
