// 
// Decompiled by Procyon v0.5.36
// 

package id.git.utils;

import id.git.model.Param;
import java.util.Map;
import java.util.Properties;
import id.git.model.Database;
import org.apache.log4j.Logger;

public class Config
{
    private static Logger log;
    
    static {
        Config.log = Logger.getLogger(Config.class.getName());
    }
    
    public static boolean init() {
        final Properties prop = Files.getProperties("config.properties");
        if (prop.isEmpty()) {
            return false;
        }
        Log.configure("config.properties");
        Function.printStatus("START PROGRAM");
        Config.log.info((Object)Function.printStatus("[OK]", new Object[] { "File", "config.properties" }));
        Database.configure(prop);
        final Map<String, String[]> commonParam = SQLData.getCommonParam(new String[] { "SEND START DATE", "SEND FINISH DATE", "SEND START TIME", "SEND FINISH TIME", "wa.product", "wa.type", "wa.type", "wa.id", "wa.url", "wa.version", "wa.token" }, false);
        final Map<String, String[]> mailParam_01 = SQLData.getEmailParam(new String[] { "mail.smtp.host", "mail.smtp.port", "smtp.sender", "smtp.password", "mail.pop.host" }, false);
        final Map<String, String[]> mailParam_2 = SQLData.getContent();
        System.out.println(mailParam_2);
        if (commonParam.isEmpty() || mailParam_01.isEmpty() || mailParam_2.isEmpty()) {
            return false;
        }
        System.out.println("masuk");
        setCommonParam(commonParam);
        prop.clear();
        return true;
    }
    
    private static void setCommonParam(final Map<String, String[]> commonParam) {
        Param.configure(commonParam.get("SEND START DATE")[0], commonParam.get("SEND FINISH DATE")[0], commonParam.get("SEND START TIME")[0], commonParam.get("SEND FINISH TIME")[0], commonParam.get("wa.product")[0], commonParam.get("wa.id")[0], commonParam.get("wa.url")[0], commonParam.get("wa.version")[0], commonParam.get("wa.token")[0], commonParam.get("wa.type")[0]);
    }
}
