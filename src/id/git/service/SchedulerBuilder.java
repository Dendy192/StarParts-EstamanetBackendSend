// 
// Decompiled by Procyon v0.5.36
// 

package id.git.service;

import org.quartz.Trigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.ScheduleBuilder;
import org.quartz.CronScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.JobBuilder;
import org.quartz.impl.StdSchedulerFactory;
import id.git.utils.Function;
import org.quartz.Job;
import org.apache.log4j.Logger;

public class SchedulerBuilder
{
    private static Logger log;
    
    static {
        SchedulerBuilder.log = Logger.getLogger(SchedulerBuilder.class.getName());
    }
    
    public SchedulerBuilder(final Class<? extends Job> jobClass, final String startTime, final String finishTime, final String groupName) {
        Function.printStatus("SCHEDULER: Running");
        SchedulerBuilder.log.info((Object)Function.printStatus("[OK]", new Object[] { "Class ", jobClass.getName(), "Start ", startTime, "Finish", finishTime, "Group ", groupName }));
        try {
            final Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            JobDetail jobDetail = JobBuilder.newJob((Class)jobClass).withIdentity("job1", groupName).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", groupName).withSchedule((ScheduleBuilder)CronScheduleBuilder.cronSchedule(Function.expressionDaily(startTime))).build();
            scheduler.scheduleJob(jobDetail, trigger);
            if (finishTime != null) {
                jobDetail = JobBuilder.newJob((Class)jobClass).withIdentity("job2", groupName).build();
                trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", groupName).withSchedule((ScheduleBuilder)CronScheduleBuilder.cronSchedule(Function.expressionDaily(finishTime))).build();
                scheduler.scheduleJob(jobDetail, trigger);
            }
            scheduler.start();
        }
        catch (SchedulerException e) {
            SchedulerBuilder.log.error((Object)"Exception!!!", (Throwable)e);
        }
    }
}
