// 
// Decompiled by Procyon v0.5.36
// 

package id.git.service;

import org.quartz.JobExecutionException;
import id.git.utils.Function;
import org.quartz.JobExecutionContext;
import org.apache.log4j.Logger;
import org.quartz.Job;

public class JobMontly implements Job
{
    private static Logger log;
    public static boolean isRunning;
    
    static {
        JobMontly.log = Logger.getLogger(JobMontly.class.getName());
        JobMontly.isRunning = false;
    }
    
    public void execute(final JobExecutionContext context) throws JobExecutionException {
        if (JobMontly.isRunning) {
            try {
                Function.printStatus("SCHEDULER: Timeout!");
                JobMontly.isRunning = false;
                Function.exit();
            }
            catch (Exception e) {
                e.printStackTrace();
                JobMontly.log.error((Object)Function.getErrMsg(e));
            }
        }
        else {
            Function.printStatus("SCHEDULER: Starting");
            JobMontly.isRunning = true;
            final SendEngine se = new SendEngine();
            se.engine();
        }
    }
}
