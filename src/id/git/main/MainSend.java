// 
// Decompiled by Procyon v0.5.36
// 

package id.git.main;

import org.quartz.Job;
import id.git.service.JobMontly;
import id.git.service.SchedulerBuilder;
import id.git.utils.Function;
import id.git.model.Param;
import id.git.utils.Config;

public class MainSend
{
    public static void main(final String[] args) {
        if (Config.init()) {
            if (Function.isSchedule(Param.getStartDate(), Param.getFinishDate())) {
                new SchedulerBuilder((Class<? extends Job>)JobMontly.class, Param.getStartTime(), Param.getFinishTime(), "groupMonthly");
            }
        }
        else {
            System.err.println("Error configuration!");
            System.exit(0);
        }
    }
}
