package com.technologygarden.testdem;

import cn.hutool.core.util.ZipUtil;
import com.technologygarden.dao.EnterpriseInformationMapper;
import com.technologygarden.entity.EnterpriseInformation;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.util.FilUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class TestController {
    @Autowired
    EnterpriseInformationMapper enterpriseInformationMapper;

    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        return "ttt";
    }

    @RequestMapping(value = "/insert")
    public ResultBean insert(Model model) {
        EnterpriseInformation ee = new EnterpriseInformation();
        ee.setCName("维他命");
        enterpriseInformationMapper.insertReturnPrimaryKey(ee);
        System.out.println("#######" + ee.getCId());
        return new ResultBean();
    }

    @GetMapping("/test")
    public ResponseEntity<byte[]> srad(String fileName, HttpServletRequest request) {
        System.out.println("@@@@@@@" + fileName);
        try {
            ResponseEntity<byte[]> responseEntity = FilUploadUtils.downloadFile(fileName);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    //        public ResponseEntity<byte[]> downZip(List<String> fileNameList) throws IOException {
//        List<File> fileList = new ArrayList<>();
//        for (String fileName : fileNameList) {
//            File file = new File(FilUploadUtils.getFileStoragePath() + fileName);
//            //判断文件是否存在
//            if (file.exists()) {
//                fileList.add(file);
//            }
//        }
    @GetMapping("/downZip")
    public ResponseEntity<byte[]> downZip() throws FileNotFoundException {
        List<File> fileList = new ArrayList<>();
        File file1 = new File(FilUploadUtils.getFileStoragePath() + "axis.txt");
        File file2 = new File(FilUploadUtils.getFileStoragePath() + "Femalewarrior.jpg");

        fileList.add(file1);
        fileList.add(file2);
        //定义压缩文件名字
        String name = System.currentTimeMillis() + ".zip";
        //压缩文件存放地址
        String path = FilUploadUtils.getFileStoragePath() + name;
        File zipfile = new File(path);
        if (!zipfile.getParentFile().exists()) {
            zipfile.getParentFile().mkdirs();
        }
        FileOutputStream fos2 = new FileOutputStream(zipfile);
        FilUploadUtils.toZip(fileList, fos2);
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


