package com.activity.service.etl.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * ETL Scheduler which used to execute all the ETL tasks one by one.
 */
@Component
public class ETLScheduler {

    private static Logger log = LoggerFactory.getLogger(ETLScheduler.class);

    @Autowired
    private UserScoreSettlementETLTask userScoreSettlementETLTask;

    /**
     * Execute all the ETL task one by one according to their dependencies at mid-night.
     */
    @Scheduled(cron = "30 0/30 * * * *")
    public void startSettlementETLTasks() {
        if (log.isTraceEnabled()) log.trace("Settlement ETL tasks started.");

        userScoreSettlementETLTask.execute();
        if (log.isTraceEnabled()) log.trace("Settlement ETL tasks finished.");
    }

}
