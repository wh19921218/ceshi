package com.cn.lianshou.TimerTask;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FileName: com.cn.lianshou.TimerTask.QuartzJobExample.java
 * Author: Wanghh
 * Date: 2018/4/3 19:58
 */
public class QuartzJobExample implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "★★★★★★★★★★★");
    }
}
