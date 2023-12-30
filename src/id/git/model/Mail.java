// 
// Decompiled by Procyon v0.5.36
// 

package id.git.model;

import id.git.utils.Function;
import org.apache.log4j.Logger;
import java.io.Serializable;

public class Mail implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static Logger log;
    private static String host;
    private static int port;
    private static String user;
    private static String pwd;
    private static String sender;
    private static String subject;
    private static String content;
    private static String imgPublic;
    private static String pop3host;
    private static String pop3user;
    private static String pop3pwd;
    
    static {
        Mail.log = Logger.getLogger(Mail.class.getName());
        Mail.host = null;
        Mail.port = 0;
        Mail.user = null;
        Mail.pwd = null;
        Mail.sender = null;
        Mail.subject = null;
        Mail.content = null;
        Mail.imgPublic = null;
        Mail.pop3host = null;
        Mail.pop3user = null;
        Mail.pop3pwd = null;
    }
    
    public static void configure(final String host, final int port, final String user, final String pwd, final String subject, final String content, final String pop3host) {
        setHost(host);
        setPort(port);
        setUser(user);
        setPwd(pwd);
        setSubject(subject);
        setContent(content);
        setPop3host(pop3host);
        Mail.log.info((Object)Function.printStatus("[OK]", new Object[] { "Smtp Host  ", getHost(), "Smtp Port  ", getPort(), "Smtp User  ", getUser(), "Smtp Pwd   ", getPwd(), "Pop3 Host  ", getPop3host(), "Subject", getSubject(null), "Content", getContent(null, null) }));
    }
    
    public static String getHost() {
        return Mail.host;
    }
    
    public static void setHost(final String host) {
        Mail.host = host;
    }
    
    public static int getPort() {
        return Mail.port;
    }
    
    public static void setPort(final int port) {
        Mail.port = port;
    }
    
    public static String getUser() {
        return Mail.user;
    }
    
    public static void setUser(final String user) {
        Mail.user = user;
    }
    
    public static String getPwd() {
        return Mail.pwd;
    }
    
    public static void setPwd(final String pwd) {
        Mail.pwd = pwd;
    }
    
    public static String getSender() {
        return Mail.sender;
    }
    
    public static void setSender(final String sender) {
        Mail.sender = sender;
    }
    
    public static String getSubject(final String periodSubject) {
        if (periodSubject == null) {
            return Mail.subject;
        }
        return Function.replacePeriod(Mail.subject, periodSubject);
    }
    
    public static void setSubject(final String subject) {
        Mail.subject = subject;
    }
    
    public static String getPop3host() {
        return Mail.pop3host;
    }
    
    public static void setPop3host(final String pop3host) {
        Mail.pop3host = pop3host;
    }
    
    public static String getPop3user() {
        return Mail.pop3user;
    }
    
    public static void setPop3user(final String pop3user) {
        Mail.pop3user = pop3user;
    }
    
    public static String getPop3pwd() {
        return Mail.pop3pwd;
    }
    
    public static void setPop3pwd(final String pop3pwd) {
        Mail.pop3pwd = pop3pwd;
    }
    
    public static String getContent(final String name, final String period) {
        String replaceContent = "";
        if (name == null && period == null) {
            return Mail.content;
        }
        replaceContent = Function.replaceUsername(Mail.content, name);
        replaceContent = Function.replacePeriod(replaceContent, period);
        replaceContent = Function.replacePeriodEng(replaceContent, period);
        return replaceContent;
    }
    
    public static void setContent(final String content) {
        Mail.content = content;
    }
    
    public static void setImgPublic(String imgPublic) {
        if (imgPublic == null || imgPublic.trim().equals("")) {
            imgPublic = "";
        }
        else {
            imgPublic = "<br/><br/><img src=\"" + imgPublic + " \">";
        }
    }
    
    public static String getImgPublic() {
        return Mail.imgPublic;
    }
}
