// 
// Decompiled by Procyon v0.5.36
// 

package id.git.model;

import id.git.utils.Function;
import org.apache.log4j.Logger;
import java.io.Serializable;

public class Param implements Serializable
{
    private static Logger log;
    private static final long serialVersionUID = 1L;
    private static String startDate;
    private static String finishDate;
    private static String finishTime;
    private static String startTime;
    private static String product;
    private static String url;
    private static String token;
    private static String type;
    
    static {
        Param.log = Logger.getLogger(Param.class.getName());
        Param.startDate = null;
        Param.finishDate = null;
        Param.finishTime = null;
        Param.startTime = null;
        Param.product = null;
        Param.url = null;
        Param.token = null;
        Param.type = null;
    }
    
    public static void configure(final String startDate, final String finishDate, final String startTime, final String finishTime, final String product, final String id, final String urlTMP, final String version, final String token, final String type) {
        final String url1 = String.valueOf(urlTMP) + "/" + version + "/" + id + "/";
        System.out.println("dari param: " + url1);
        setStartDate(startDate);
        setFinishDate(finishDate);
        setStartTime(startTime);
        setFinishTime(finishTime);
        setUrl(url1);
        setToken(token);
        setType(type);
        setProduct(product);
        Param.log.info((Object)Function.printStatus("[OK]", new Object[] { "SEND Start date", getStartDate(), "SEND Finish date", getFinishDate(), "SEND Start time", getStartTime(), "SEND Finish time", getFinishTime(), "type", getType(), "product", getProduct(), "URL", getUrl(), "token", getToken() }));
    }
    
    public static String getStartDate() {
        return Param.startDate;
    }
    
    public static void setStartDate(final String startDate) {
        Param.startDate = startDate;
    }
    
    public static String getFinishDate() {
        return Param.finishDate;
    }
    
    public static void setFinishDate(final String finishDate) {
        Param.finishDate = finishDate;
    }
    
    public static String getStartTime() {
        return Param.startTime;
    }
    
    public static void setStartTime(final String startTime) {
        Param.startTime = startTime;
    }
    
    public static String getFinishTime() {
        return Param.finishTime;
    }
    
    public static void setFinishTime(final String finishTime) {
        Param.finishTime = finishTime;
    }
    
    public static long getSerialversionuid() {
        return 1L;
    }
    
    public static Logger getLog() {
        return Param.log;
    }
    
    public static void setLog(final Logger log) {
        Param.log = log;
    }
    
    public static String getProduct() {
        return Param.product;
    }
    
    public static void setProduct(final String product) {
        Param.product = product;
    }
    
    public static String getUrl() {
        return Param.url;
    }
    
    public static void setUrl(final String urlTMP) {
        Param.url = urlTMP;
    }
    
    public static String getToken() {
        return Param.token;
    }
    
    public static void setToken(final String token) {
        Param.token = token;
    }
    
    public static String getType() {
        return Param.type;
    }
    
    public static void setType(final String type) {
        Param.type = type;
    }
}
