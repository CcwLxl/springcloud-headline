package com.heima.common.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.heima.utils.common.IdsUtils;

import java.io.IOException;

/**
 * 反序列化器（输入）
 * 作用：当 JSON 转成 Java 对象时，把字符串形式的 ID（如 "123456"）转回 Long 或 Integer。
 * 目的：支持前端传入加密后的 ID，后端自动解密。
 */
public class ConfusionDeserializer extends JsonDeserializer<Object> {

    JsonDeserializer<Object>  deserializer = null;
    JavaType type =null;

    public  ConfusionDeserializer(JsonDeserializer<Object> deserializer, JavaType type){
        this.deserializer = deserializer;
        this.type = type;
    }

    @Override
    public  Object deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException{
        try {
            if(type!=null){
                if(type.getTypeName().contains("Long")){
                    return Long.valueOf(p.getValueAsString());
                }
                if(type.getTypeName().contains("Integer")){
                    return Integer.valueOf(p.getValueAsString());
                }
            }
            return IdsUtils.decryptLong(p.getValueAsString());
        }catch (Exception e){
            if(deserializer!=null){
                return deserializer.deserialize(p,ctxt);
            }else {
                return p.getCurrentValue();
            }
        }
    }
}
