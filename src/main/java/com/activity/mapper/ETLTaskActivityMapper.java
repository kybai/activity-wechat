package com.activity.mapper;

import com.activity.model.ETLTaskActivity;

/**
 * @author Create by ky.bai on 2018-02-28 18:35
 */
public interface ETLTaskActivityMapper {

    public ETLTaskActivity findByName(String name);

    public ETLTaskActivity save(ETLTaskActivity record);

    public ETLTaskActivity update(ETLTaskActivity record);
}
