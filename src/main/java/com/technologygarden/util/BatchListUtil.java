package com.technologygarden.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchListUtil<E> {

    /**
     * 把list分成多个批次
     * @param list 集合
     * @param batchSize 批次大小
     * @return Map<Integer,List<E>>
     */
    public Map<Integer, List<E>> batchList(List<E> list, int batchSize){
        Map<Integer,List<E>> itemMap = new HashMap<>();
        itemMap.put(1, new ArrayList<>());
        for(E e : list){
            List<E> batchList= itemMap.get(itemMap.size());
            if(batchList.size() == batchSize){//当list满足批次数量，新建一个list存放后面的数据
                batchList = new ArrayList<>();
                itemMap.put(itemMap.size()+1, batchList);
            }
            batchList.add(e);
        }
        return itemMap;
    }


}
