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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;

@Slf4j
@Controller
@Api(tags = "文件接口", value = "FileController")
public class FileController {

    @Value("${file.storagePath}")
    private String fileStoagePath;

    @RequestMapping(value = "/image/{image_name}", method = RequestMethod.GET)
    @ApiOperation(value = "获取图片", notes = "参数包括：图片名称（包括文件后缀）")
    public ResponseEntity<byte[]> getImage(@PathVariable("image_name") String image_name) throws Exception{

        byte[] imageContent;
        String path = fileStoagePath + image_name;
        File file = new File(path);

        // 判断图片是否存在
        if(!file.exists()){
            log.warn(path + "：该图片不存在。");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 获取图片类型
        String type = FileTypeUtil.getType(file);
        imageContent = FilUploadUtils.imageToByte(file, type);

        final HttpHeaders headers = new HttpHeaders();
        if(type.equals("jpg") || type.equals("jpeg")){
            headers.setContentType(MediaType.IMAGE_JPEG);
        }else if(type.equals("png")){
            headers.setContentType(MediaType.IMAGE_PNG);
        }
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

}
