package com.upchina.spring.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.IOException;

/**
 * Created by anjunli on  2021/11/11
 **/
//@Component
public class SyncUserGroup {
    private String md5Key = "mwKWpCnYotwIq9oh8tXIiazUSCyE7KS6xe1APw4DtJp8ifWlySGV4K7SkhzfP0ZU";

    @Autowired
    Host host;

    @Scheduled(cron = "0/10 * * * * ?")
    public void getActiveGroupsByToken(){
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        long timeStamp = System.currentTimeMillis();
        String token = DigestUtils.md5DigestAsHex((md5Key+timeStamp).getBytes());
        Request request = new Request.Builder()
                .url("http://"+host.getHost()+"/ifs-management/getActiveGroupsByToken?timestamp="+timeStamp+"&token="+token+"&page_no=1&page_size=10")
                .method("POST", body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            JSONObject jsonObject = JSON.parseObject(string);
            System.out.println(jsonObject.get("datas"));
            JSONArray objects = JSON.parseArray(jsonObject.toJSONString());
            System.out.println(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void getGroupListByFundAccount(){

    }
}
@Component
class Host {
    @Value("${host}")
    private String host;

    public Host() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}

