package com.cn.lianshou.TimerTask;

import com.cn.lianshou.entity.SystemConfig;
import com.cn.lianshou.mapper.SystemConfigMapper;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

/**
 * FileName: com.cn.lianshou.TimerTask.QuartzTest.java
 * Author: Wanghh
 * Date: 2018/4/3 20:00
 */
public class QuartzTest {


    @Autowired
    private static SystemConfigMapper systemConfigMapper;

    public void quartz() {
        /*try {

            SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
            Scheduler sche = gSchedulerFactory.getScheduler();
            String job_name = "动态任务调度";
            System.out.println("【系统启动】开始(每1秒输出一次)...");
            QuartzManager.addJob(job_name,QuartzJobExample.class , "0/1 * * * * ?", sche);

            Thread.sleep(3000);
            System.out.println("【修改时间】开始(每2秒输出一次)...");
            QuartzManager.modifyJobTime("", job_name, "10/2 * * * * ?");
            Thread.sleep(4000);
            System.out.println("【移除定时】开始...");
            QuartzManager.removeJob(job_name);
            System.out.println("【移除定时】成功");

            System.out.println("【再次添加定时任务】开始(每10秒输出一次)...");
            QuartzManager.addJob(job_name, QuartzJobExample.class, "*//*10 * * * * ?",sche);
            Thread.sleep(30000);
            System.out.println("【移除定时】开始...");
            QuartzManager.removeJob(job_name);
            System.out.println("【移除定时】成功");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public static void main(String[] args) {
        try {
            String job_name = "计算逾期1";
            System.out.println("【系统启动】开始(每1秒输出一次)...");
            Calendar cal = Calendar.getInstance();
            cal.add( Calendar.SECOND,10);
            QuartzManager.addJob(job_name, CalOverdueJob.class, "0 08 09 ? * *",job_name);

            /*SystemConfig systemConfig = new SystemConfig();

            systemConfig.set

            systemConfigMapper.save();*/


            /*Thread.sleep(20);
            cal.setTime( new Date());
            cal.add( Calendar.SECOND,10);
            QuartzManager.addJob(job_name+1, QuartzJobExample.class, QuartzManager.formatTime(cal.getTime()),job_name+1);
            Thread.sleep(11);*/
//      System.out.println("【修改时间】开始(每2秒输出一次)...");
//      QuartzManager.modifyJobTime(job_name, "0 40 16 ? * *");
//      Thread.sleep(6000);
//      System.out.println("【移除定时】开始...");
//      QuartzManager.removeJob(job_name);
//      System.out.println("【移除定时】成功");
//
//      System.out.println("【再次添加定时任务】开始(每10秒输出一次)...");
//      QuartzManager.addJob(job_name, QuartzJob.class, "*/10 * * * * ?");
//      Thread.sleep(60000);
//      System.out.println("【移除定时】开始...");
//      QuartzManager.removeJob(job_name);
//      System.out.println("【移除定时】成功");

            QuartzManager.startJobs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
