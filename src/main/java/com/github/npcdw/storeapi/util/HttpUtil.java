package com.github.npcdw.storeapi.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Http接口工具类
 */
public class HttpUtil {
    private static final CloseableHttpClient httpClient;
    private static final PoolingHttpClientConnectionManager cm;

    static {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory()).build();

        cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(700);
        cm.setDefaultMaxPerRoute(50);
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(8000)
                .setConnectTimeout(8000).setSocketTimeout(8000).build();

        httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig)
                .evictExpiredConnections()
                .evictIdleConnections(60, TimeUnit.SECONDS).setConnectionManager(cm).build();
    }

    public static CloseableHttpResponse post(HttpPost httpPost) throws IOException {
        return httpClient.execute(httpPost);
    }

    public static CloseableHttpResponse get(HttpGet httpGet) throws IOException {
        return httpClient.execute(httpGet);
    }

}
