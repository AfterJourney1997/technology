package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.technologygarden.dao.LegalPersonMapper;
import com.technologygarden.entity.LegalPerson;
import com.technologygarden.entity.ResultBean.ResultBean;
import com.technologygarden.service.CompanyLegalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyLegalPersonService")
public class CompanyLegalPersonServiceImpl implements CompanyLegalPersonService {

    private final LegalPersonMapper legalPersonMapper;

    @Autowired
    public CompanyLegalPersonServiceImpl(LegalPersonMapper legalPersonMapper) {
        this.legalPersonMapper = legalPersonMapper;
    }

    @Override
    public ResultBean<PageInfo<?>> getLegalPersonByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<LegalPerson> legalPersonList = legalPersonMapper.selectLegalPersonByPage();
        PageInfo<?> pageInfo = new PageInfo<>(legalPersonList);
        return new ResultBean<>(pageInfo);
    }

    @Override
    public ResultBean<?> insertLegalPerson(LegalPerson legalPerson) {

        legalPersonMapper.insert(legalPerson);
        return new ResultBean<>();
    }

    @Override
    public ResultBean<PageInfo<?>> searchLegalPersonByPage(Integer pageNum, Integer pageSize, String legalPersonName, String companyName) {

        PageHelper.startPage(pageNum, pageSize);
        Page<LegalPerson> legalPersonList = legalPersonMapper.searchLegalPersonByPage(legalPersonName, companyName);
        PageInfo<?> pageInfo = new PageInfo<>(legalPersonList);
        return new ResultBean<>(pageInfo);
    }

}
