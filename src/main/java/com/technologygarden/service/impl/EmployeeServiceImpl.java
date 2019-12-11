package com.technologygarden.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.technologygarden.dao.DegreeMapper;
import com.technologygarden.dao.EmployeeMapper;
import com.technologygarden.dao.PoliticsStatusMapper;
import com.technologygarden.entity.DeclareAward;
import com.technologygarden.entity.Employee;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.EmployeeService;
import com.technologygarden.util.FilUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("EmployeeService")
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final DegreeMapper degreeMapper;
    private final PoliticsStatusMapper politicsStatusMapper;
    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper, DegreeMapper degreeMapper, PoliticsStatusMapper politicsStatusMapper) {
        this.employeeMapper = employeeMapper;
        this.degreeMapper = degreeMapper;
        this.politicsStatusMapper = politicsStatusMapper;
    }

    @Override
    public ResultBean<Page<Employee>> selectByPage(Integer pageNum, Integer pageSize,Integer cId) throws IOException {
        PageHelper.startPage(pageNum,pageSize);
        Page<Employee> employeeList=employeeMapper.selectByPage(cId);
        for(Employee employee:employeeList){
            String fileNameString= employee.getFileName();
            String fileNameArray []=fileNameString.split("/");
            List<String> fileNameList=new ArrayList<>();
            List<String> filePathList=new ArrayList<>();
            for(int i=0;i<fileNameArray.length;i++){
                filePathList.add(FilUploadUtils.getFilePath()+"\\"+fileNameArray[i]);
                fileNameList.add(FilUploadUtils.getfileName(fileNameArray[i]));
            }
            employee.setFileNameList(fileNameList);
            employee.setFilePathList(filePathList);
        }
        return new ResultBean<>(employeeList);
    }

    @Override
    public ResultBean insertEmployee(MultipartFile[] blFile, Employee employee) throws IOException {
        String []fileNameList=new String[blFile.length];
        String UUName;
        int i=0;
        for (MultipartFile file:blFile){
            UUName=FilUploadUtils.saveFile(file);
            fileNameList[i]=UUName;
            i++;
        }
        String fileName = ArrayUtil.join(fileNameList, "/");
        employee.setFileName(fileName);
        employee.setCId(employee.getInfoid());
        return new ResultBean(employeeMapper.insert(employee));
    }

    @Override
    public ResultBean updateEmployee(MultipartFile blFile[], Employee employee) throws IOException {
        String []fileNameList=new String[blFile.length];
        String UUName;
        int i=0;
        for (MultipartFile file:blFile){
            UUName=FilUploadUtils.saveFile(file);
            fileNameList[i]=UUName;
            i++;
        }
        String fileName = ArrayUtil.join(fileNameList, "/");
        employee.setFileName(fileName);
        return new ResultBean(employeeMapper.updateByPrimaryKey(employee));
    }

    @Override
    public ResultBean deleteEmployee(Integer eId) throws IOException {
        Employee employee=employeeMapper.selectByPrimaryKey(eId);
        String fileNameString= employee.getFileName();
        String fileNameArray []=fileNameString.split("/");
        for(int i=0;i<fileNameArray.length;i++) {
            FilUploadUtils.deleteFile(fileNameArray[i]);
        }
        return new ResultBean(employeeMapper.deleteByPrimaryKey(eId));
    }
    @Override
    public ResultBean<Page<Employee>> selectByNamePage(Integer pageNum, Integer pageSize,Integer cId,String employeeName) throws IOException {
        System.out.println("pageNum:"+pageNum+"pageSize:"+pageSize+"cId:"+cId+"employeeName:"+employeeName);
        PageHelper.startPage(pageNum,pageSize);
        Page<Employee> employeeList=employeeMapper.selectByNamePage(cId,employeeName);
        for(Employee employee:employeeList){
            String fileNameString= employee.getFileName();
            String fileNameArray []=fileNameString.split("/");
            List<String> fileNameList=new ArrayList<>();
            List<String> filePathList=new ArrayList<>();
            for(int i=0;i<fileNameArray.length;i++){
                filePathList.add(FilUploadUtils.getFilePath()+"\\"+fileNameArray[i]);
                fileNameList.add(FilUploadUtils.getfileName(fileNameArray[i]));
            }
            employee.setFileNameList(fileNameList);
            employee.setFilePathList(filePathList);
        }
        return new ResultBean<>(employeeList);
    }
}
