package com.pig4cloud.pigx.device.generator.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpRequestUtils {

    public static String sendPost() {

        HttpClient httpClient;
        HttpPost postMethod;
        HttpResponse response;
        String reponseContent = null;
        String url = "https://open.ys7.com/api/lapp/device/add?accessToken=at.abvh67wbaq8sbv332rz9sj742ie9445l-1otcscneq9-1ng5hox-f6exxkfyr&deviceSerial=D30249783&validateCode=SCJWNO";
        try {
            httpClient = HttpClients.createDefault();
            postMethod = new HttpPost(url);
            //postMethod.addHeader("Content-type", "application/json; charset=utf-8");
            //设置请求头
            postMethod.addHeader("Content-type", "application/x-www-form-urlencoded");
            response = httpClient.execute(postMethod);
            HttpEntity httpEntity = response.getEntity();
            reponseContent = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reponseContent;
    }

}