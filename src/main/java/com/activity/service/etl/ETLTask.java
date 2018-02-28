/**
 *
 */
package com.activity.service.etl;

import com.activity.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

/**
 * ETL Task used to extract, transform and load data into the fact tables and dimension tables.
 * ETL Task will analysis the delta data (inserted, updated, deleted) which happened
 * between last analysis time and current time, not the full data, for best performance purpose.
 * The current time to execute the analysis task will be recorded in the ETLTaskActivity table to
 * be as the last success time.
 */
public abstract class ETLTask {

    private String taskName = "default";

    @Autowired
    ETLTaskActivityService etlTaskActivityService;

    public ETLTask() {
    }

    public ETLTask(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Execute the ETL task and update the task execution time
     */
    public void execute() {
        Timestamp analysisStartTime = this.getLastSuccessTime();
        Timestamp analysisEndTime = DateUtils.getCurrentTimestamp();

        this.taskExecute(analysisStartTime, analysisEndTime);

        this.updateSuccessTime(analysisEndTime);
    }

    /**
     * Get last successful execution time by task name
     *
     * @return 1970-1-1 0:0:0 if it's the first time to execute the etl task,
     * else it will return the last success time to execute the etl task
     */
    private Timestamp getLastSuccessTime() {
        return etlTaskActivityService.getLastSuccessTime(this.taskName);
    }

    /**
     * Update last successful execution time by task name
     * Here use the current time when start this task as the last successful time
     */
    private void updateSuccessTime(Timestamp executionTime) {
        etlTaskActivityService.updateLastSuccessTime(this.taskName, executionTime);
    }

    /**
     * Analysis the original data and store the result into the fact table and dimension table
     *
     * @param startTime, last success time to execute the etl task
     * @param endTime,   current time
     */
    public abstract void taskExecute(Timestamp startTime, Timestamp endTime);

    /**
     * Get the name of the ETL task
     *
     * @return ETL Task's name
     */
    public String getTaskName() {
        return this.taskName;
    }
}
