//package com.xproduct.esdemo;
//
//import java.net.InetAddress;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by corning on 2018/3/5.
// */
//@Configuration
//public class MyConfig {
//
//    @Bean
//    public TransportClient client() throws UnknownHostException {
//
//        Settings settings = Settings.builder().put("cluster.name", "corning").build();
//        PreBuiltTransportClient client = new PreBuiltTransportClient(settings);
//
//        int[] local_ports = {9300, 9310, 9320};
//        byte[] bs = new byte[] { (byte) 192, (byte) 168, (byte)52, (byte)130 };
//
//        for (int port : local_ports) {
////            client.addTransportAddress(new TransportAddress(
////                    InetAddress.getByName(bs), port
////            )
//
//            try {
//                client = new PreBuiltTransportClient(Settings.EMPTY)
//                        .addTransportAddress(new TransportAddress(InetAddress.getByAddress(bs), 9300));
//            }catch (UnknownHostException e) {
//                LOG.error("创建elasticsearch客户端失败");
//                e.printStackTrace();
//            }
//        }
//        return client;
//    }
//}