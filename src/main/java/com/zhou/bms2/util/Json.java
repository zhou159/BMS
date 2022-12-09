package com.zhou.bms2.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Json对象（该项目定制化简易Json对象）
 *
 * @author zhouxiong
 * @version v1.0
 * 2022/10/24 14:47
 */
public class Json {
    private Map<String, Object> node;
    
    public Json(){
        node = new HashMap<>();
    }
    
    public Object get(String key){
        return node.get(key);
    }
    
    public void put(String key, Object value){
        node.put(key, value);
    }
    
    @Override
    public String toString(){
        return JsonUtil.map2JsonString(node);
    }
}
