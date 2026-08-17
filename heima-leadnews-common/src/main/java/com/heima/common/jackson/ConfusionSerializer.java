package com.heima.common.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 序列化器（输出）
 * 作用：当 Java 对象转成 JSON 时，把 ID 字段（如 123456）直接序列化为字符串（如 "123456"）。
 * 目的：防止前端 JavaScript 丢失精度（JavaScript 的 Number 无法精确表示 19 位 Long 类型）。
 */
public class ConfusionSerializer extends JsonSerializer<Object> {

    @Override
    public  void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        try {
            if (value != null) {
                jsonGenerator.writeString(value.toString());
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        serializers.defaultSerializeValue(value, jsonGenerator);
    }
}
