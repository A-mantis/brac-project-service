package com.mantis.brac.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @author: wei.wang
 * @since: 2020/4/3 0:08
 * @history: 1.2020/4/3 created by wei.wang
 */
public class DataUtil {

    private static Logger logger = LoggerFactory.getLogger(DataUtil.class);

    private static Random random = new Random();

    private static final Pattern dbName2JavaNamePattern = Pattern.compile("(_[\\w])");

    /**
     * 获取UUID
     *
     * @return UUID
     */
    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 格林时间：Thu May 18 2017 00:00:00 GMT+0800 (中国标准时间) 格式转换
     *
     * @param datdString
     * @return
     */
    public static String parseTime(String datdString) {
        datdString = datdString.replace("GMT", "").replaceAll("\\(.*\\)", "");
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        Date dateTrans = null;
        try {
            dateTrans = format.parse(datdString);
            return new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(dateTrans).replace("-", "/");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datdString;
    }

    /**
     * 获取当前时间
     *
     * @param dateFormat
     * @return
     */
    public static String getCurrentDate(String dateFormat) {
        //当前时间
        Date dNow = new Date();
        //得到日历
        Calendar calendar = Calendar.getInstance();
        //把当前时间赋给日历
        calendar.setTime(dNow);
        //设置时间格式
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(dNow);
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date).getTime());
        } catch (Exception e) {
            logger.info("date2TimeStamp {}", e.getMessage());
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public static String timeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 移除字符串左边的0
     *
     * @param str
     * @return
     */
    public static String removeLeftZero(String str) {
        if (strEmpty(str)) {
            return null;
        }
        str = str.replaceAll("^(0*)", "");
        if (strEmpty(str)) {
            return null;
        }
        return str;
    }

    /**
     * 移除字符串右边的0
     *
     * @param str
     * @return
     */
    public static String removeRightZero(String str) {
        if (strEmpty(str)) {
            return null;
        }
        str = str.replaceAll("0*$", "");
        if (strEmpty(str)) {
            return null;
        }
        return str;
    }

    /**
     * 校验数字相等   2.0 2.00    2
     *
     * @param str
     * @param tar
     * @return
     */
    public static boolean numberEqual(String str, String tar) {
        try {
            return Double.parseDouble(str) == Double.parseDouble(tar);
        } catch (Exception e) {
            //如果抛出异常，返回False
            return false;
        }
    }

    /**
     * 移除.0
     *
     * @param str
     * @return
     */
    public static String removePointZero(String str) {
        if (strEmpty(str)) {
            return null;
        }
        str = str.replaceAll(".0+$", "");
        if (strEmpty(str)) {
            return null;
        }
        return str;
    }

    /**
     * 移除字符串尾部的字符串
     *
     * @param target
     * @return
     */
    public static String removeTail(String target, int length) {
        if (strEmpty(target)) {
            return "";
        }
        if (target.length() < length) {
            return "";
        }
        if (length == 0) {
            return target;
        }
        return target.substring(0, target.length() - length);
    }

    /**
     * 判断字符是否为空
     *
     * @param value
     * @return
     */
    public static boolean strEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }


    /**
     * 获取随机指定长度字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        random.ints(length, 0, base.length()).forEach(x -> sb.append(base.charAt(x)));
        return sb.toString();
    }

    /**
     * 判断是否相等
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean equals(Object obj1, Object obj2) {
        return obj1 != null ? obj1.equals(obj2) : obj2 == null;
    }

    /**
     * 判断是否是数字
     *
     * @param obj
     * @return
     */
    public static boolean isNum(Object obj) {
        try {
            Integer.parseInt(obj.toString());
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    /**
     * 判断是否是Windows系统
     *
     * @return
     */
    public static Boolean isWinOs() {
        return System.getProperty("os.name").toLowerCase().startsWith("win");
    }

    /**
     * 转换数据格式为int
     *
     * @param val
     * @return
     */
    public static Integer toInt(Object val) {
        if (val instanceof Double) {
            return ((Double) val).intValue();
        } else {
            return Integer.valueOf(val.toString());
        }
    }

    /**
     * 数据库字段名称转换为JAVA字段名称
     *
     * @param dbName
     * @return
     */
    public static String dbName2JavaName(String dbName) {
        dbName = dbName.toLowerCase();
        String s;
        for (Matcher m = dbName2JavaNamePattern.matcher(dbName); m.find(); dbName = dbName.replaceAll(s, s.substring(1).toUpperCase())) {
            s = m.group(0);
        }
        return dbName;
    }

    /**
     * 大写转下划线
     *
     * @param name
     * @return
     */
    public static String upper2Underline(String name) {
        return name.replaceAll("(?<=[a-z])(?=[A-Z])", "_");
    }

    /**
     * 删除后缀
     *
     * @param str
     * @param suffix
     * @return
     */
    public static String removeSuffix(String str, String suffix) {
        return !strEmpty(str) && !strEmpty(suffix) ? (str.endsWith(suffix) ? str.substring(0, str.length() - suffix.length()) : str) : str;
    }


    public static Map<String, Object> caseInsensitiveMap(Map<String, Object> map) {
        Map<String, Object> tempMap = new HashMap();
        Iterator var2 = map.keySet().iterator();

        while (var2.hasNext()) {
            String key = (String) var2.next();
            tempMap.put(key.toLowerCase(), map.get(key));
        }

        return tempMap;
    }

    public static <K, V> V getFirstOrNull(Map<K, V> map) {
        V obj = null;
        Iterator var2 = map.entrySet().iterator();

        while (var2.hasNext()) {
            Map.Entry<K, V> entry = (Map.Entry) var2.next();
            obj = entry.getValue();
            if (obj != null) {
                break;
            }
        }

        return obj;
    }


}
