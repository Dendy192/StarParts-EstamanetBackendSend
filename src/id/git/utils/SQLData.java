// 
// Decompiled by Procyon v0.5.36
// 

package id.git.utils;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.PreparedStatement;
import java.sql.Connection;
import id.git.conn.DBEngine;
import org.apache.log4j.Logger;

public class SQLData
{
    private static Logger log;
    
    static {
        SQLData.log = Logger.getLogger(SQLData.class.getName());
    }
    
    public static void InsertLogSCH(final String start, final String finish, final int total, final int suc, final int fail, final String type) {
        Connection conn = null;
        PreparedStatement ps = null;
        final String sql = "INSERT INTO \"SP_LOG_SCH\" (\"LOG_SCH_SD\", \"LOG_SCH_FD\", \"LOG_SCH_TOTAL\", \"LOG_SCH_SUC\", \"LOG_SCH_FAIL\", \"LOG_SCH_TYPE\")VALUES('" + start + "', '" + finish + "', '" + total + "', '" + suc + "', '" + fail + "', '" + type + "')";
        try {
            System.out.println(sql);
            SQLData.log.info((Object)sql);
            conn = DBEngine.getConnection();
            ps = conn.prepareStatement(sql);
            final int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("success");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
            return;
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public static void updateLog(final String id, final String status, final String message, final String period) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "";
        try {
            if (status.equals("Y")) {
                sql = "UPDATE \"SP_LOG\" SET   \"LOG_STATUS\"='" + status + "', \"LOG_MESSAGE\"='" + message + "'" + " WHERE \"CUSTOMER_ID\"='" + id + "' AND \"LOG_PERIOD\" = '" + period + "'";
            }
            else if (status.equals("R")) {
                sql = "UPDATE \"SP_LOG\" SET   \"LOG_MESSAGE\"='" + message + "'" + " WHERE \"CUSTOMER_ID\"='" + id + "' AND \"LOG_PERIOD\" = '" + period + "'";
            }
            conn = DBEngine.getConnection();
            SQLData.log.info((Object)sql);
            ps = conn.prepareStatement(sql);
            final int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Success");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
            return;
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public static HashMap<String, String> getInvoice(final String period) {
        final HashMap<String, String> result = new HashMap<String, String>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        final String sql = "SELECT DISTINCT \"CUSTOMER_ID\", \"LOG_PATH_PDF\" FROM \"SP_LOG\" WHERE \"LOG_PERIOD\"='" + period + "' AND \"LOG_STATUS\"='R'";
        try {
            conn = DBEngine.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getString("CUSTOMER_ID"), rs.getString("LOG_PATH_PDF"));
            }
        }
        catch (Exception e) {
            SQLData.log.info((Object)e.getMessage());
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
            return result;
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        return result;
    }
    
    public static List<String> getCustomerPhone(final String id) {
        final List<String> result = new ArrayList<String>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        final String sql = "SELECT \"OUTLET_NAME\", \"OUTLET_PHONE\" FROM \"SP_OUTLET\" WHERE \"OUTLET_ID\" = '" + id + "'";
        try {
            conn = DBEngine.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("OUTLET_NAME"));
                result.add(rs.getString("OUTLET_PHONE"));
            }
        }
        catch (Exception e) {
            SQLData.log.info((Object)e.getMessage());
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
            
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
       
        return result;
    }
    
    public static Map<String, String[]> getContent() {
        final Map<String, String[]> result = new HashMap<String, String[]>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBEngine.getConnection();
            final String sql = "SELECT content_id , content_d FROM content ;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                final String id = rs.getString("content_id");
                final String[] conte = { rs.getString("content_d") };
                result.put(id, conte);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            SQLData.log.error((Object)Function.getErrMsg(e));
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
                SQLData.log.error((Object)Function.getErrMsg(e2));
            }
            return result;
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
                SQLData.log.error((Object)Function.getErrMsg(e2));
            }
        }
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
            SQLData.log.error((Object)Function.getErrMsg(e2));
        }
        return result;
    }
    
    public static Map<String, String[]> getEmailParam(final String[] key, final boolean isClob) {
        SQLData.log.info((Object)("key=" + Function.split(key) + ", isClob=" + isClob));
        if (isClob) {
            return getParameter("SELECT content_id , content_d FROM content WHERE", "content_id", key, isClob);
        }
        return getParameter("select \"parameter_Name\", \"parameter_Value\" from \"parameter\" WHERE ", "\"parameter_Name\"", key, isClob);
    }
    
    public static Map<String, String[]> getCommonParam(final String[] key, final boolean isClob) {
        SQLData.log.info((Object)("key=" + Function.split(key) + ", isClob=" + isClob));
        return getParameter("select \"parameter_Name\", \"parameter_Value\" from \"parameter\" WHERE ", "\"parameter_Name\"", key, isClob);
    }
    
    private static Map<String, String[]> getParameter(final String sql, final String field, final String[] key, final boolean isClob) {
        SQLData.log.info((Object)("sql=" + sql + ", field=" + field + ", key=" + Function.split(key) + ", isClob=" + isClob));
        if (isClob) {
            return DBEngine.execute(String.valueOf(sql) + Function.whereSQL(field, key), isClob);
        }
        return DBEngine.execute(String.valueOf(sql) + Function.whereSQL(field, key), isClob);
    }
}
