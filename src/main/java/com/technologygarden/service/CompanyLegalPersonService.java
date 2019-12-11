package com.technologygarden.service;

import com.github.pagehelper.Page;
import com.technologygarden.entity.LegalPerson;
import com.technologygarden.entity.ResultBean.ResultBean;

public interface CompanyLegalPersonService {

    ResultBean<Page<LegalPerson>> getLegalPersonByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertLegalPerson(LegalPerson legalPerson);
}
