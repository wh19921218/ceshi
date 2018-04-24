package com.cn.lianshou.controllrer;

import com.cn.lianshou.TimerTask.CalOverdueJob;
import com.cn.lianshou.TimerTask.QuartzManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

/**
 * FileName: com.cn.lianshou.controllrer.Timer.java
 * Author: Wanghh
 * Date: 2018/4/11 17:59
 */
@Controller
public class Timer {

    @RequestMapping(value = "/xxx.do")
    public void xx(){

        try {
            String job_name = "计算逾期2";
            System.out.println("【系统启动】开始(每1秒输出一次)...");
            Calendar cal = Calendar.getInstance();
            cal.add( Calendar.SECOND,10);

            System.out.println("【移除定时】开始...");
            QuartzManager.removeJob(job_name);

            QuartzManager.addJob(job_name, CalOverdueJob.class, "0 20 10 ? * *",job_name);

            QuartzManager.startJobs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
