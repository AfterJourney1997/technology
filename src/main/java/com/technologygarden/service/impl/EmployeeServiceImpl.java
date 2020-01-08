package com.technologygarden.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.DegreeMapper;
import com.technologygarden.dao.EmployeeMapper;
import com.technologygarden.dao.PoliticsStatusMapper;
import com.technologygarden.entity.DeclareAward;
import com.technologygarden.entity.Degree;
import com.technologygarden.entity.Employee;
import com.technologygarden.entity.PoliticsStatus;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.EmployeeService;
import com.technologygarden.util.FilUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
    public ResultBean<PageInfo<?>> selectByPage(Integer pageNum, Integer pageSize, Integer cId) throws IOException {
        PageHelper.startPage(pageNum,pageSize);
        Page<Employee> employeeList=employeeMapper.selectByPage(cId);
        for(Employee employee:employeeList){
            employee.setZName(politicsStatusMapper.selectByPrimaryKey(employee.getZId()).getZName());
            employee.setXName(degreeMapper.selectByPrimaryKey(employee.getXId()).getXName());
            String fileNameString= employee.getFileName();
            if(!StringUtils.isEmpty(fileNameString)){
                //判断文件是否存在
                String fileNameArray []=fileNameString.split("/");
                List<String> fileNameList=new ArrayList<>();
                List<String> filePathList=new ArrayList<>();
                for(int i=0;i<fileNameArray.length;i++){
                    filePathList.add(FilUploadUtils.getImageShowPath()+fileNameArray[i]);
                    fileNameList.add(fileNameArray[i]);
                }
                employee.setFileNameList(fileNameList);
                employee.setFilePathList(filePathList);
            }

        }
        PageInfo<?> pageInfo = new PageInfo<>(employeeList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<PoliticsStatus> selectAllByPoliticsStatus() {
        List<PoliticsStatus> politicsStatusList=politicsStatusMapper.selectAll();
        return new ResultBean(politicsStatusList);
    }

    @Override
    public ResultBean<Degree> selectAllByDegree() {
        return new ResultBean(degreeMapper.selectAll());
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
        if(blFile.length>0) {
            //删除原来的文件
            String fileNameString = employee.getFileName();
            if (!StringUtils.isEmpty(fileNameString)) {
                String fileNameArray[] = fileNameString.split("/");
                for (int i = 0; i < fileNameArray.length; i++) {
                    FilUploadUtils.deleteFile(fileNameArray[i]);
                }
            }


            String[] fileNameList = new String[blFile.length];
            String UUName;
            int i = 0;
            for (MultipartFile file : blFile) {
                UUName = FilUploadUtils.saveFile(file);
                fileNameList[i] = UUName;
                i++;
            }
            if (fileNameList.length > 0) {
                String fileName = ArrayUtil.join(fileNameList, "/");
                employee.setFileName(fileName);
            }
        }
        return new ResultBean(employeeMapper.updateByPrimaryKey(employee));
    }

    @Override
    public ResultBean deleteEmployee(Integer eId) throws IOException {
        Employee employee=employeeMapper.selectByPrimaryKey(eId);
        //删除文件
        String fileNameString= employee.getFileName();
        if(!StringUtils.isEmpty(fileNameString)){
            String fileNameArray []=fileNameString.split("/");
            for(int i=0;i<fileNameArray.length;i++) {
                FilUploadUtils.deleteFile(fileNameArray[i]);
            }
        }
        return new ResultBean(employeeMapper.deleteByPrimaryKey(eId));
    }
    @Override
    public ResultBean<PageInfo<?>> selectByNamePage(Integer pageNum, Integer pageSize,Integer cId,String employeeName) throws IOException {
        System.out.println("pageNum:"+pageNum+"pageSize:"+pageSize+"cId:"+cId+"employeeName:"+employeeName);
        PageHelper.startPage(pageNum,pageSize);
        Page<Employee> employeeList=employeeMapper.selectByNamePage(cId,employeeName);
        for(Employee employee:employeeList){
            employee.setZName(politicsStatusMapper.selectByPrimaryKey(employee.getZId()).getZName());
            employee.setXName(degreeMapper.selectByPrimaryKey(employee.getXId()).getXName());
            String fileNameString= employee.getFileName();
            if(fileNameString.length()>0){
                //判断文件是否存在
                String fileNameArray []=fileNameString.split("/");
                List<String> fileNameList=new ArrayList<>();
                List<String> filePathList=new ArrayList<>();
                for(int i=0;i<fileNameArray.length;i++){
                    filePathList.add(FilUploadUtils.getImageShowPath()+fileNameArray[i]);
                    fileNameList.add(fileNameArray[i]);
                }
                employee.setFileNameList(fileNameList);
                employee.setFilePathList(filePathList);
            }
        }
        PageInfo<?> pageInfo = new PageInfo<>(employeeList);
        return new ResultBean<>(pageInfo);
    }

}
