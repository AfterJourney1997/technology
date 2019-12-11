package com.technologygarden.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
    public ResultBean<Page<LegalPerson>> getLegalPersonByPage(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        Page<LegalPerson> legalPersonList = legalPersonMapper.selectLegalPersonByPage();
        return new ResultBean<>(legalPersonList);
    }

    @Override
    public ResultBean<?> insertLegalPerson(LegalPerson legalPerson) {

        legalPersonMapper.insert(legalPerson);
        return new ResultBean<>();
    }

}
