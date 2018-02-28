package com.activity.service.etl;

import java.sql.Timestamp;

/**
 * Get the ETL task's last success time or update it after the execution of the ETL task.
 */
public interface ETLTaskActivityService {

    /**
     * Get last success time according to task name
     * If it's the first time to execute this task, return 1970-1-1 0:0:0
     *
     * @return the last success time of the task,
     * or 1970-1-1 0:0:0 if this is the first time to execute it
     */
    public Timestamp getLastSuccessTime(String taskName);

    /**
     * Update last success time according to task name
     *
     * @param taskName
     * @param executionTime
     *
     * @return true - update successfully, false - failed to update it
     */
    public boolean updateLastSuccessTime(String taskName, Timestamp executionTime);

}
