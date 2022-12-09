package com.zhou.bms2.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * http请求工具类（该项目定制化工具类）
 *
 * @author zhouxiong
 * @version v1.0
 * 2022/10/24 13:57
 */
public class HttpUtil {
    public static String doPost(String httpUrl, String params){
        String result = null;
        HttpURLConnection urlConnection = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try{
            URL url = new URL(httpUrl);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(60000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            outputStream = urlConnection.getOutputStream();
            outputStream.write(params.getBytes());
            if (urlConnection.getResponseCode() == 200) {
    
                inputStream = urlConnection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    
                StringBuilder sbf = new StringBuilder();
                String temp;
                // 循环遍历一行一行读取数据
                while ((temp = bufferedReader.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭资源
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            urlConnection.disconnect();
        }
        return result;
    }
}
