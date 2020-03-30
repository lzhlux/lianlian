package cn.com.tichain.gateway.service;

import cn.com.tichain.gateway.mdel.Account;
import cn.com.tichain.gateway.mdel.AccountCredentials;
import cn.com.tichain.gateway.mdel.AccountCredentials.JpushBean;
import cn.com.tichain.gateway.mdel.AccountDTO;
import cn.com.tichain.gateway.middleware.PreRequestLogFilter;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.sun.javafx.collections.MappingChange.Map;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;

@Service
public class AccountService {
    public static final com.squareup.okhttp.MediaType JSON = com.squareup.okhttp.MediaType.parse("application/json; charset=utf-8");
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    OkHttpClient client = new OkHttpClient();

    Gson gson = new Gson();

    @Autowired
    private DiscoveryClient discoveryClient;

    public String getUrl() {
        return url;
    }

    private String url = null;
    private RestTemplate restTemplate = new RestTemplate();

    @Bean
    public String setUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances("account");
        if (list != null && list.size() > 0 ) {
            this.url =  list.get(0).getUri().toString();
        }
        return this.url;
    }



    public AccountDTO login(AccountCredentials creds) throws Exception{

        RequestBody body = RequestBody.create(JSON, gson.toJson(creds));
        Request request = new Request.Builder()
                .url(url + "/api/v1/login")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String res = response.body().string();
        Type jsonType = new TypeToken<AccountDTO<Object>>() {
        }.getType();
        AccountDTO accountDTO = gson.fromJson(res, jsonType);

        if(!accountDTO.isSuccess()) {
            throw new Exception("登录失败:" + accountDTO.getMessage());
        }

        Type jsonType2 = new TypeToken<AccountDTO<Account>>() {
        }.getType();
        AccountDTO accountDTO2 = gson.fromJson(res, jsonType2);

        return accountDTO2;
    }
    public AccountDTO adminlogin(HttpServletRequest req, AccountCredentials creds) throws Exception{
        String addr = req.getHeader("X-Real-IP");
        String ua = req.getHeader("User-Agent");
        RequestBody body = RequestBody.create(JSON, gson.toJson(creds));
        Request request = new Request.Builder()
                .addHeader("User-Agent", ua)
                .addHeader("X-Real-IP", addr)
                .url(url + "/api/v1/admin/login")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String res = response.body().string();
        Type jsonType = new TypeToken<AccountDTO<Object>>() {
        }.getType();
        AccountDTO accountDTO = gson.fromJson(res, jsonType);

        if(!accountDTO.isSuccess()) {
            throw new Exception("登录失败:" + accountDTO.getMessage());
        }

        Type jsonType2 = new TypeToken<AccountDTO<Account>>() {
        }.getType();
        AccountDTO accountDTO2 = gson.fromJson(res, jsonType2);

        return accountDTO2;
    }
//    public AccountDTO findByUsername(String account) throws Exception{
//
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(url + "/api/v1/find/account?account=" + account)
//                .build();
//        Response response = okHttpClient.newCall(request).execute();
//        String res = response.body().string();
//        Type jsonType = new TypeToken<AccountDTO<Object>>() {
//        }.getType();
//        AccountDTO accountDTO = gson.fromJson(res, jsonType);
//
//        if(!accountDTO.isSuccess()) {
//            throw new Exception("登录失败:" + accountDTO.getMessage());
//        }
//
//        Type jsonType2 = new TypeToken<AccountDTO<Account>>() {
//        }.getType();
//        AccountDTO accountDTO2 = gson.fromJson(res, jsonType2);
//
//        return accountDTO2;
//    }
}
