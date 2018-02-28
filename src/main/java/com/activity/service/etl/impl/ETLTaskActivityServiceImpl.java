package com.activity.service.etl.impl;

import com.activity.mapper.ETLTaskActivityMapper;
import com.activity.model.ETLTaskActivity;
import com.activity.service.etl.ETLTaskActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ETLTaskActivityServiceImpl implements ETLTaskActivityService {

    @Autowired
    ETLTaskActivityMapper etlTaskActivityMapper;

    @Override
    public Timestamp getLastSuccessTime(String taskName) {
        ETLTaskActivity etlTaskActivity = etlTaskActivityMapper.findByName(taskName);

        if (null == etlTaskActivity) return (new Timestamp(1970, 1, 1, 0, 0, 0, 0));
        else return etlTaskActivity.getSuccessTime();
    }

    @Override
    public boolean updateLastSuccessTime(String taskName, Timestamp executionTime) {
        ETLTaskActivity etlTaskActivity = etlTaskActivityMapper.findByName(taskName);

        if (null == etlTaskActivity) {
            // first time to execute the etl Task
            etlTaskActivity = new ETLTaskActivity();

            etlTaskActivity.setName(taskName);
            etlTaskActivity.setSuccessTime(executionTime);
            etlTaskActivity.setSuccess(true);

            etlTaskActivity = etlTaskActivityMapper.save(etlTaskActivity);
        } else {
            // Not the first time, update it
            etlTaskActivity.setSuccessTime(executionTime);
            etlTaskActivity.setSuccess(true);

            etlTaskActivity = etlTaskActivityMapper.update(etlTaskActivity);
        }

        return (null != etlTaskActivity && etlTaskActivity.getId() > 0);
    }

}
