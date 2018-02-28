package com.activity.service.etl.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ETL Scheduler which used to execute all the ETL tasks one by one.
 */
@Component
public class ETLScheduler {

    private static Logger log = LoggerFactory.getLogger(ETLScheduler.class);

    /**
     * Execute all the ETL task one by one according to their dependencies at mid-night.
     */
    @Scheduled(cron = "1 30 23 * * *")
    public void startAllETLTasks() {
        if (log.isTraceEnabled()) log.trace("ETL tasks started.");

        //indexProjectNameETLTask.execute();
        if (log.isTraceEnabled()) log.trace("ETL tasks finished.");
    }

}
