package com.zhou.bms2.util;

import java.util.Map;

/**
 * 字符串工具类（该项目定制化工具类）
 * 时间原因，部分地方改用fastjson
 *
 * @author zhouxiong
 * @version v1.0
 * 2022/10/24 14:27
 */
public class JsonUtil {
    public static String map2JsonString(Map<String, Object> map){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (Map.Entry<String, Object> next : map.entrySet()) {
            String key = next.getKey();
            Object value = next.getValue();
            stringBuilder.append("\"").append(key).append("\"").append(":").append("\"").append(value).append("\"").append(",");
        }
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
    
    public static Json string2Json(String str){
        Json json = new Json();
        
        //去除最外层大括号
        String substring = str.substring(1, str.length() - 1);
        //去除换行符，制表符
        String replace = substring.replace("\t", "").replace("\n", "").replace("\r", "");
        
        // 根据逗号分割字符串，每个元素便是一个结点
        // todo 有点不严谨，如果value中也存在逗号，则会出错
        String[] split = replace.split(",");
        
        for (String s : split) {
            String[] node = s.split(":", 2);
            if (node[1].contains("{")) {
                json.put(node[0].replace("\"", ""), string2Json(node[1]));
            } else {
                json.put(node[0].replace("\"", ""), node[1].replace("\"", ""));
            }
        }
        return json;
    }
}
