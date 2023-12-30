// 
// Decompiled by Procyon v0.5.36
// 

package id.git.model;

import org.apache.log4j.Logger;
import java.io.Serializable;

public class WaParam implements Serializable
{
    private static Logger log;
    private static final long serialVersionUID = 1L;
    private static String product;
    private static String url;
    private static String token;
    private static String type;
    
    static {
        WaParam.log = Logger.getLogger(WaParam.class.getName());
        WaParam.product = null;
        WaParam.url = null;
        WaParam.token = null;
        WaParam.type = null;
    }
    
    public static void configure(final String product, final String id, final String urlTMP, final String version, final String token, final String type) {
        final String url = String.valueOf(urlTMP) + "/" + version + "/" + id;
        setUrl(url);
        setToken(token);
        setType(type);
        setProduct(product);
    }
    
    public static long getSerialversionuid() {
        return 1L;
    }
    
    public static String getProduct() {
        return WaParam.product;
    }
    
    public static void setProduct(final String product) {
        WaParam.product = product;
    }
    
    public static String getUrl() {
        return WaParam.url;
    }
    
    public static void setUrl(final String urlTMP) {
        WaParam.url = urlTMP;
    }
    
    public static String getToken() {
        return WaParam.token;
    }
    
    public static void setToken(final String token) {
        WaParam.token = token;
    }
    
    public static String getType() {
        return WaParam.type;
    }
    
    public static void setType(final String type) {
        WaParam.type = type;
    }
}
