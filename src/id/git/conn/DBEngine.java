// 
// Decompiled by Procyon v0.5.36
// 

package id.git.conn;

import java.util.ArrayList;
import java.util.List;
import java.sql.Clob;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
import java.sql.DriverManager;
import id.git.model.Database;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import org.apache.log4j.Logger;

public class DBEngine
{
    private static Logger log;
    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet rs;
    
    static {
        DBEngine.log = Logger.getLogger(DBEngine.class.getName());
    }
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.getUrl(), Database.getUser(), Database.getPwd());
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            DBEngine.log.info((Object)("Conntection DB: " + e.getMessage()));
        }
        return conn;
    }
    
    public static Map<String, String[]> execute(final String sql, final boolean isClob) {
        final Map resultMap = new HashMap();
        DBEngine.conn = getConnection();
        if (DBEngine.conn != null) {
            try {
                DBEngine.ps = DBEngine.conn.prepareStatement(sql);
                DBEngine.rs = DBEngine.ps.executeQuery();
                if (!DBEngine.rs.next()) {
                    DBEngine.log.info((Object)"No data found");
                }
                else {
                    final int columnCount = DBEngine.rs.getMetaData().getColumnCount();
                    do {
                        final String[] row = new String[columnCount - 1];
                        for (int i = 0; i < row.length; ++i) {
                            if (isClob) {
                                final Clob clob = DBEngine.rs.getClob(i + 2);
                                if (clob != null) {
                                    row[i] = clob.getSubString(1L, (int)clob.length());
                                }
                            }
                            else {
                                row[i] = DBEngine.rs.getString(i + 2);
                            }
                        }
                        resultMap.put(DBEngine.rs.getString(1), row);
                    } while (DBEngine.rs.next());
                    DBEngine.log.info((Object)("[OK] " + resultMap.size() + " Rows and " + columnCount + " Column"));
                }
            }
            catch (SQLException e) {
                DBEngine.log.error((Object)"Exception!!!", (Throwable)e);
            }
            catch (Exception e2) {
                DBEngine.log.error((Object)"Exception!!!", (Throwable)e2);
            }
        }
        return (Map<String, String[]>)resultMap;
    }
    
    public static List<String[]> execute(final String sql) {
        final List resultList = new ArrayList();
        DBEngine.conn = getConnection();
        if (DBEngine.conn != null) {
            Label_0219: {
                try {
                    DBEngine.ps = DBEngine.conn.prepareStatement(sql);
                    DBEngine.rs = DBEngine.ps.executeQuery();
                    if (!DBEngine.rs.next()) {
                        DBEngine.log.info((Object)"No data found");
                        break Label_0219;
                    }
                    final int columnCount = DBEngine.rs.getMetaData().getColumnCount();
                    do {
                        final String[] row = new String[columnCount];
                        for (int i = 0; i < row.length; ++i) {
                            row[i] = DBEngine.rs.getString(i + 1);
                        }
                        resultList.add(row);
                    } while (DBEngine.rs.next());
                    DBEngine.log.info((Object)("[OK] " + resultList.size() + " Rows and " + columnCount + " Column"));
                }
                catch (SQLException e) {
                    DBEngine.log.error((Object)"Exception!!!", (Throwable)e);
                }
                catch (Exception e2) {
                    DBEngine.log.error((Object)"Exception!!!", (Throwable)e2);
                }
                finally {
                    close();
                }
            }
            close();
        }
        return (List<String[]>)resultList;
    }
    
    public static void execute(final String sql, final String[] parameter) {
        DBEngine.conn = getConnection();
        if (DBEngine.conn == null) {
            return;
        }
        try {
            DBEngine.ps = DBEngine.conn.prepareStatement(sql);
            for (int i = 0; i < parameter.length; ++i) {
                DBEngine.ps.setObject(i + 1, parameter[i]);
            }
            DBEngine.ps.executeUpdate();
            DBEngine.log.info((Object)"[OK]");
        }
        catch (SQLException e) {
            DBEngine.log.error((Object)"Exception!!!", (Throwable)e);
        }
        catch (Exception e2) {
            DBEngine.log.error((Object)"Exception!!!", (Throwable)e2);
        }
        finally {
            close();
        }
        close();
    }
    
    private static void close() {
        if (DBEngine.rs != null) {
            try {
                DBEngine.rs.close();
            }
            catch (SQLException e) {
                DBEngine.log.error((Object)"Exception!!!", (Throwable)e);
            }
            catch (Exception e2) {
                DBEngine.log.error((Object)"Exception!!!", (Throwable)e2);
            }
        }
        if (DBEngine.ps != null) {
            try {
                DBEngine.ps.close();
            }
            catch (SQLException e) {
                DBEngine.log.error((Object)"Exception!!!", (Throwable)e);
            }
            catch (Exception e2) {
                DBEngine.log.error((Object)"Exception!!!", (Throwable)e2);
            }
        }
        if (DBEngine.conn == null) {
            return;
        }
        try {
            DBEngine.conn.close();
        }
        catch (SQLException e) {
            DBEngine.log.error((Object)"Exception!!!", (Throwable)e);
        }
        catch (Exception e2) {
            DBEngine.log.error((Object)"Exception!!!", (Throwable)e2);
        }
    }
    
    public static List<String[]> executeList(final String sql, final String[] parameter) {
        final List resultList = new ArrayList();
        DBEngine.conn = getConnection();
        if (DBEngine.conn != null) {
            Label_0251: {
                try {
                    DBEngine.ps = DBEngine.conn.prepareStatement(sql);
                    for (int i = 0; i < parameter.length; ++i) {
                        DBEngine.ps.setObject(i + 1, parameter[i]);
                    }
                    DBEngine.rs = DBEngine.ps.executeQuery();
                    if (!DBEngine.rs.next()) {
                        DBEngine.log.info((Object)"No data found");
                        break Label_0251;
                    }
                    final int columnCount = DBEngine.rs.getMetaData().getColumnCount();
                    do {
                        final String[] row = new String[columnCount];
                        for (int j = 0; j < row.length; ++j) {
                            row[j] = DBEngine.rs.getString(j + 1);
                        }
                        resultList.add(row);
                    } while (DBEngine.rs.next());
                    DBEngine.log.info((Object)("[OK] " + resultList.size() + " Rows and " + columnCount + " Column"));
                }
                catch (SQLException e) {
                    DBEngine.log.error((Object)"Exception!!!", (Throwable)e);
                }
                catch (Exception e2) {
                    DBEngine.log.error((Object)"Exception!!!", (Throwable)e2);
                }
                finally {
                    close();
                }
            }
            close();
        }
        return (List<String[]>)resultList;
    }
}
