// 
// Decompiled by Procyon v0.5.36
// 

package id.git.utils;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.ConsoleAppender;

public class Log
{
    private static String defaultPattern;
    private static ConsoleAppender a;
    private static DailyRollingFileAppender b;
    
    static {
        Log.defaultPattern = "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}.%M:%L - %m%n";
    }
    
    public static void changeLayout(final String pattern) {
        Log.a.setLayout((Layout)new PatternLayout(pattern));
        Log.b.setLayout((Layout)new PatternLayout(pattern));
    }
    
    public static void defaultLayout() {
        Log.a.setLayout((Layout)new PatternLayout(Log.defaultPattern));
        Log.b.setLayout((Layout)new PatternLayout(Log.defaultPattern));
    }
    
    private static void initAppender() {
        Log.a = (ConsoleAppender)Logger.getRootLogger().getAppender("stdout");
        Log.b = (DailyRollingFileAppender)Logger.getRootLogger().getAppender("file");
    }
    
    static void configure(final String file) {
        PropertyConfigurator.configure(file);
        initAppender();
    }
}
