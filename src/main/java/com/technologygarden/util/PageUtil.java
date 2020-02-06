package com.technologygarden.util;


import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;


// 自定义List分页工具
public class PageUtil {

    public static <E> PageInfo<E> startPage(List<E> list, Integer pageNum, Integer pageSize) {

        PageInfo<E> pageInfo = new PageInfo<>();

        if (list == null || list.size() == 0) {

            pageInfo.setList(new ArrayList<>());
            return pageInfo;

        }

        // 记录总数
        Integer count = list.size();
        // 页数
        Integer pageCount;
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        // 开始索引
        int fromIndex;
        // 结束索引
        int toIndex;

        if (pageNum > pageCount) {
            pageNum = pageCount;
        }
        if (!pageNum.equals(pageCount)) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List<E> pageList = list.subList(fromIndex, toIndex);

        pageInfo.setTotal(count);
        pageInfo.setList(pageList);

        return pageInfo;
    }

}

