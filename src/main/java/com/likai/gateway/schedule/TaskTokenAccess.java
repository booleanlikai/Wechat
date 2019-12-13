package com.likai.gateway.schedule;

import com.likai.gateway.model.token.tokenResponse;
import com.likai.gateway.service.Imp.accessTokenGetImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
@Configuration
@EnableScheduling
public class TaskTokenAccess {

    private final static Logger logger = LoggerFactory.getLogger(TaskTokenAccess.class);
    private final static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    private accessTokenGetImpl accessTokenGetImpl;


    @PostConstruct
    public void initial() {
        getTokenAccessByTime();
    }

    @Scheduled(cron = "0 0 0/2 * * ?")
    public void getTokenAccessByTime() {
        tokenResponse tokenResponse = accessTokenGetImpl.getaccess();
        if (tokenResponse != null && (tokenResponse.getErrcode() == null || tokenResponse.getErrcode().equals(""))) {
            TokenAccessFactory.setAccess_token(tokenResponse.getAccess_token());
            TokenAccessFactory.setStatus(true);
        } else if (!TokenAccessFactory.isFlag()) {
            TokenAccessFactory.setFlag(true);
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            // 参数：1、任务体 2、首次执行的延时时间
            //      3、任务执行间隔 4、间隔时间单位
            ScheduledFuture<?> t = service.scheduleAtFixedRate(
                    () -> {
                        tokenResponse tokenResponse1 = accessTokenGetImpl.getaccess();
                        if (tokenResponse1 != null && (tokenResponse1.getErrcode() == null || tokenResponse1.getErrcode().equals(""))) {
                            TokenAccessFactory.setAccess_token(tokenResponse1.getAccess_token());
                            TokenAccessFactory.setStatus(true);
                            TokenAccessFactory.setFlag(false);
                            service.shutdownNow();
                        }
                    },
                    0,
                    3,
                    TimeUnit.MINUTES);
        }
    }

}
