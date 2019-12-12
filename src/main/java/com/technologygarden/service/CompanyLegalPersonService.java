package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.LegalPerson;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface CompanyLegalPersonService {

    ResultBean<PageInfo<?>> getLegalPersonByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertLegalPerson(LegalPerson legalPerson);

    ResultBean<PageInfo<?>> searchLegalPersonByPage(Integer pageNum, Integer pageSize, String legalPersonName, String companyName);
}
