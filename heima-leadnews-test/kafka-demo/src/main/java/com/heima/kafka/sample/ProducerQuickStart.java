package com.heima.kafka.sample;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 生产者
 */
public class ProducerQuickStart {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.kafka的配置信息
        Properties properties = new Properties();
        //kafka的连接地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        //发送失败，失败的重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG,5);
        //消息key的序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //消息value的序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        //ack配置，消息确认机制
        properties.put(ProducerConfig.ACKS_CONFIG,"all");

        //重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG,10);

        //数据压缩
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");

        //2.生产者对象
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);

        //封装发送的消息  (根据key的哈希值，决定放到哪个分区下)
        ProducerRecord<String,String> record = new ProducerRecord<String, String>("ccw-topic",0,"key--01","test kafka");

        //3.发送消息
//        producer.send(record);

//1、同步方式发送消息（同步可能产生阻塞，一般采用异步发送）
//        RecordMetadata recordMetadata = producer.send(record).get();
//        System.out.println(recordMetadata.offset());  //打印偏移量

//2、异步方式发送消息
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    System.out.println("记录异常信息到日志表中");
                }
                System.out.println(metadata.offset());
            }
        });


        //4.关闭消息通道，必须关闭，否则消息发送不成功
        producer.close();
    }

}