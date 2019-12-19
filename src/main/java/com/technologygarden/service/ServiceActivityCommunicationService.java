package com.technologygarden.service;

import com.github.pagehelper.PageInfo;
import com.technologygarden.entity.ActivityCommunication;
import com.technologygarden.entity.ResultBean.ResultBean;
import org.springframework.web.multipart.MultipartFile;

public interface ServiceActivityCommunicationService {

    ResultBean<PageInfo<?>> getActivityCommunicationListByPage(Integer pageNum, Integer pageSize);

    ResultBean<?> insertActivityCommunication(MultipartFile file, ActivityCommunication activityCommunication);

    ResultBean<?> deleteActivityCommunicationById(Integer activityCommunicationId);

    ResultBean<?> updateActivityCommunicationById(MultipartFile file, ActivityCommunication activityCommunication);

    ResultBean<PageInfo<?>> searchActivityCommunicationListByPage(Integer pageNum, Integer pageSize, Integer activityCategoryId, String activityName);
}
