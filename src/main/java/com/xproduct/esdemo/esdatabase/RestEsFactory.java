package com.xproduct.esdemo.esdatabase;


import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.log4j.Logger;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.net.URI;


public class RestEsFactory {
    private static Logger log = Logger.getLogger(RestEsFactory.class);
    private static RestHighLevelClient client = null;
    private static URI getUrl = null;
    private static String getIndex = null;

    private static RestClientBuilder builder = null;

    /**
     * set RestHighLevelClient's parameter
     * "192.168.32.140", 9200, "http"
     *
     * @return RestHighLevelClient
     */
    public static RestHighLevelClient getRestClient() {
        try{
            if (client == null) {
                if (EsClientParameter.checkPara()) {
                    getUrl = URI.create(EsClientParameter.esURL);
                    getIndex = EsClientParameter.esIndexName;
                    init();
                } else {
                    log.error("es client not correct!");
                }
            }
        } catch (Exception e) {
            log.error("faile accdessing es server");
            client = null;
        }

        return client;
    }
     private static void init() throws Exception {
         builder = RestClient.builder(new HttpHost(getUrl.getHost(),getUrl.getPort(),getUrl.getScheme()));
         checkConnetTimeOut();
         builder.setMaxRetryTimeoutMillis(5000000);
         client = new RestHighLevelClient(builder);
     }

     private static void checkConnetTimeOut() throws Exception {
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestCon) {
                requestCon.setConnectTimeout(10000);
                requestCon.setSocketTimeout(500000);
                requestCon.setConnectionRequestTimeout(5000);
                return requestCon;
            }
        });
     }

     public static void close() {
        if (client != null) {
            try {
                client.close();
                client = null;
            } catch (Exception e) {
                log.error("cannot close ES client!",e);
                client = null;
            }

        }
     }
}
