// 
// Decompiled by Procyon v0.5.36
// 

package id.git.model;

import java.util.Properties;
import org.apache.log4j.Logger;
import java.io.Serializable;

public class Database implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static Logger log;
    private static String driver;
    private static String url;
    private static String name;
    private static String user;
    private static String pwd;
    private static String schema;
    
    static {
        Database.log = Logger.getLogger(Database.class.getName());
        Database.driver = null;
        Database.url = null;
        Database.name = null;
        Database.user = null;
        Database.pwd = null;
        Database.schema = null;
    }
    
    public static void configure(final Properties properties) {
        Database.log.info((Object)"configure DB [OK]");
        setUrl(properties.getProperty("db.url").trim());
        setUser(properties.getProperty("db.user").trim());
        setSchema(properties.getProperty("db.schema").trim());
        setPwd(properties.getProperty("db.pwd").trim());
    }
    
    public static String getUrl() {
        return Database.url;
    }
    
    public static void setUrl(final String url) {
        Database.url = url;
    }
    
    public static String getName() {
        return Database.name;
    }
    
    public static void setName(final String name) {
        Database.name = name;
    }
    
    public static String getUser() {
        return Database.user;
    }
    
    public static void setUser(final String user) {
        Database.user = user;
    }
    
    public static String getPwd() {
        return Database.pwd;
    }
    
    public static void setPwd(final String pwd) {
        Database.pwd = pwd;
    }
    
    public static String getSchema() {
        return Database.schema;
    }
    
    public static void setSchema(final String schema) {
        Database.schema = schema;
    }
}
