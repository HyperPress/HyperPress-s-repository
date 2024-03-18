package cn.util;

public class StringUtil {
    public static boolean isEmpty(String s){
        if (s==null||"".equals(s.trim())){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isnotEmpty(String s){
        if (s==null||"".equals(s.trim())){
            return false;
        }else {
            return true;
        }
    }

}
