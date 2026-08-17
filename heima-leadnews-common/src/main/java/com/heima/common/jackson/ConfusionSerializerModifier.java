package com.heima.common.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.heima.model.common.annotation.IdEncrypt;

import java.util.ArrayList;
import java.util.List;

/**
 * 序列化修改器
 * 作用：遍历所有对象的字段，找出需要混淆的字段（有 @IdEncrypt 注解或字段名叫 id），替换成自定义的序列化器 ConfusionSerializer。
 */
public class ConfusionSerializerModifier extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
                                                     BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        List<BeanPropertyWriter> newWriter = new ArrayList<>();
        for(BeanPropertyWriter writer : beanProperties){
            String name = writer.getType().getTypeName();
            if(null == writer.getAnnotation(IdEncrypt.class) && !writer.getName().equalsIgnoreCase("id")){
                newWriter.add(writer);                                  // 保持原样，不做任何处理
            } else {
                writer.assignSerializer(new ConfusionSerializer());     //有 @IdEncrypt 注解 或 字段名是 "id"，用自定义序列化器
                newWriter.add(writer);
            }
        }
        return newWriter;
    }
}
