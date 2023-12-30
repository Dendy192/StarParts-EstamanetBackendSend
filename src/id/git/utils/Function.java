// 
// Decompiled by Procyon v0.5.36
// 

package id.git.utils;

import java.util.Calendar;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import org.apache.log4j.Logger;

public class Function
{
    private static Logger log;
    private static DateFormat dateFormat;
    private static DateFormat yyyyMMdd;
    private static StringBuilder stringBuilder;
    private static Date date;
    
    static {
        Function.log = Logger.getLogger(Function.class.getName());
        Function.dateFormat = new SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSS");
        Function.yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        Function.stringBuilder = new StringBuilder();
        Function.date = new Date();
    }
    
    public static boolean isSchedule(final String startDate, final String finishDate) {
        final int dateNow = dateNow();
        if (dateNow < Integer.parseInt(startDate) || dateNow > Integer.parseInt(finishDate)) {
            Function.log.warn((Object)printStatus("Not schedule!", new Object[] { "Date now", dateNow, "Start date", startDate, "Finish date", finishDate }));
            exit();
        }
        return true;
    }
    
    public static String split(final String[] param) {
        if (param != null) {
            final StringBuffer sb = new StringBuffer();
            final String[] arrayOfString = param;
            for (int j = param.length, i = 0; i < j; ++i) {
                final String s = arrayOfString[i];
                sb.append("[").append(s).append("]");
            }
            return sb.toString();
        }
        return null;
    }
    
    public static String sqlReplace(final String sql) {
        return sql.replace("'", "''''");
    }
    
    public static String getDate() {
        return Function.dateFormat.format(Function.date.getTime());
    }
    
    public static String getDate_yyyyMMdd() {
        return Function.yyyyMMdd.format(Function.date.getTime());
    }
    
    public static String getDate_yyyyMMdd(final Date date) {
        return Function.yyyyMMdd.format(date);
    }
    
    public static String getDate(final Date date) {
        return Function.dateFormat.format(date);
    }
    
    public static String replaceDateFormat(final Date date) {
        return Function.dateFormat.format(date);
    }
    
    public static void printList(final List<String[]> dataList) {
        Function.stringBuilder.setLength(0);
        Function.stringBuilder.append("Print data");
        for (int i = 0; i < dataList.size(); ++i) {
            Function.stringBuilder.append("\n" + (i + 1) + ". ");
            for (int j = 0; j < dataList.get(i).length; ++j) {
                Function.stringBuilder.append("|").append(dataList.get(i)[j]);
            }
        }
        Function.log.info((Object)Function.stringBuilder);
    }
    
    public static String expressionDaily(final String time) {
        final String result = String.valueOf(splitBack(time)) + " * * ?";
        return result;
    }
    
    public static String splitBack(final String time) {
        final String[] arrStr = time.split(":");
        final String result = "00 " + arrStr[1] + " " + arrStr[0];
        return result;
    }
    
    public static String expDaily(final String time) {
        final String result = String.valueOf(time) + " * * ?";
        return result;
    }
    
    public static String expMonthly(final String time, final String startDate, final String endDate) {
        final String result = String.valueOf(time) + startDate + "-" + endDate + " * ?";
        return result;
    }
    
    public static void showPerformance(final long startTime) {
        final Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        final long memory = runtime.totalMemory() - runtime.freeMemory();
        String resultPerformance = "[Use memory: " + memory / 1048576L + " megabytes.]";
        Function.log.info((Object)resultPerformance);
        final long stopTime = System.currentTimeMillis();
        final long elapsedTime = (stopTime - startTime) / 1000L;
        resultPerformance = "[Times: " + elapsedTime / 60L + " minutes.]";
        Function.log.info((Object)resultPerformance);
    }
    
    public static String getErrMsg(final Exception e) {
        final StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
    
    public static String errMsg(final Exception e) {
        if (e.getCause() == null) {
            final String s = e.toString();
            return s.substring(s.indexOf(":") + 2);
        }
        return e.getCause().getLocalizedMessage();
    }
    
    public static String param(final int length) {
        final StringBuilder result = new StringBuilder();
        result.append("(");
        for (int i = 1; i < length; ++i) {
            result.append("?,");
        }
        return "?)";
    }
    
    public static String getLastMonth() {
        final Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(2, -1);
        return new SimpleDateFormat("MMyyyy").format(c.getTime());
    }
    
    public static int dateNow() {
        return Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
    }
    
    public static void exit() {
        printStatus("END PROGRAM");
        Function.log.info((Object)"Bye...");
        System.exit(0);
    }
    
    public static String search(final String input, final String key1, final String key2) {
        if (input.lastIndexOf(key1) == -1) {
            return "|unknown|";
        }
        if (input.lastIndexOf(key2) == -1) {
            return "|unknown|";
        }
        return input.substring(input.lastIndexOf(key1) + key1.length(), input.lastIndexOf(key2)).trim();
    }
    
    public static String search(final String input) {
        final String[] splitStr = input.split(";");
        if (splitStr.length == 1) {
            return splitStr[0];
        }
        if (splitStr.length == 2) {
            return splitStr[1];
        }
        return "?";
    }
    
    private static String status(final String status) {
        Function.stringBuilder.setLength(0);
        for (int mod = 50 - status.length(), i = 0; i < mod; ++i) {
            Function.stringBuilder.append("*");
            if (mod / 2 == i) {
                Function.stringBuilder.append(" " + status + " ");
            }
        }
        return Function.stringBuilder.toString();
    }
    
    public static void printStatus(final String status) {
        Log.changeLayout("%m%n");
        Function.log.info((Object)status(status));
        Log.defaultLayout();
    }
    
    public static String printStatus(final String str1, final String str2) {
        return "\n> " + str1 + "\t: " + str2;
    }
    
    public static String printStatus(final String info, final Object[] arr1) {
        Function.stringBuilder.setLength(0);
        Function.stringBuilder.append(info);
        for (int i = 0; i < arr1.length; i += 2) {
            Function.stringBuilder.append("\n> " + arr1[i] + "\t: " + arr1[i + 1]);
        }
        return Function.stringBuilder.toString();
    }
    
    public static String replacePeriod(final String content, final String period) {
        return content.replace("${period}", getPeriodSubject(period));
    }
    
    public static String replacePeriodEng(final String content, final String period) {
        return content.replace("${periodEng}", getPeriodSubjectEng(period));
    }
    
    public static String replaceUsername(final String content, final String username) {
        return content.replace("${username}", username);
    }
    
    public static String replacePassword(final String content, final String password) {
        return content.replace("${password}", password);
    }
    
    public static String whereSQL(final String field, final String[] key) {
        Function.stringBuilder.setLength(0);
        for (int i = 0; i < key.length; ++i) {
            Function.stringBuilder.append(field).append("=").append("'" + key[i] + "'");
            if (i != key.length - 1) {
                Function.stringBuilder.append(" OR ");
            }
        }
        return Function.stringBuilder.toString();
    }
    
    public static String getPeriod() {
        String result = "";
        final Calendar calendar = Calendar.getInstance();
        final int month = calendar.get(2);
        final int year = calendar.get(1);
        if (month == 0) {
            result = "12" + (year - 1);
        }
        else if (month == 1 || month == 2 || month == 3 || month == 4 || month == 5 || month == 6 || month == 7 || month == 8 || month == 9) {
            result = "0" + month + year;
        }
        else if (month == 10 || month == 11) {
            result = String.valueOf(String.valueOf(month)) + String.valueOf(year);
        }
        return result;
    }
    
    public static String getPeriodSubject(final String period) {
        String result = "";
        final String month = period.substring(0, 2);
        final String year = period.substring(2);
        if (month == "01" || month.equals("01")) {
            result = "Januari " + year;
        }
        else if (month == "02" || month.equals("02")) {
            result = "Februari " + year;
        }
        else if (month == "03" || month.equals("03")) {
            result = "Maret " + year;
        }
        else if (month == "04" || month.equals("04")) {
            result = "April " + year;
        }
        else if (month == "05" || month.equals("05")) {
            result = "Mei " + year;
        }
        else if (month == "06" || month.equals("06")) {
            result = "Juni " + year;
        }
        else if (month == "07" || month.equals("07")) {
            result = "Juli " + year;
        }
        else if (month == "08" || month.equals("08")) {
            result = "Agustus " + year;
        }
        else if (month == "09" || month.equals("09")) {
            result = "September " + year;
        }
        else if (month == "10" || month.equals("10")) {
            result = "Oktober " + year;
        }
        else if (month == "11" || month.equals("11")) {
            result = "November " + year;
        }
        else {
            result = "Desember " + year;
        }
        return result;
    }
    
    public static String getPeriodSubjectEng(final String period) {
        String result = "";
        final String month = period.substring(0, 2);
        final String year = period.substring(2);
        if (month == "01" || month.equals("01")) {
            result = "January " + year;
        }
        else if (month == "02" || month.equals("02")) {
            result = "February " + year;
        }
        else if (month == "03" || month.equals("03")) {
            result = "March " + year;
        }
        else if (month == "04" || month.equals("04")) {
            result = "April " + year;
        }
        else if (month == "05" || month.equals("05")) {
            result = "May " + year;
        }
        else if (month == "06" || month.equals("06")) {
            result = "June " + year;
        }
        else if (month == "07" || month.equals("07")) {
            result = "July " + year;
        }
        else if (month == "08" || month.equals("08")) {
            result = "August " + year;
        }
        else if (month == "09" || month.equals("09")) {
            result = "September " + year;
        }
        else if (month == "10" || month.equals("10")) {
            result = "October " + year;
        }
        else if (month == "11" || month.equals("11")) {
            result = "November " + year;
        }
        else {
            result = "December " + year;
        }
        return result;
    }
    
    public static String getTglPelaporan(final String period) {
        String result = "";
        try {
            final DateFormat sdf = new SimpleDateFormat("MMyyyy");
            final Date date1 = sdf.parse(period);
            final SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM yyyy");
            final String date2 = sdf2.format(date1);
            final Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);
            result = String.valueOf(calendar1.getActualMaximum(5)) + " " + date2;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
