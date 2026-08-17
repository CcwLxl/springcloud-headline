package com.heima.common.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.heima.model.common.annotation.IdEncrypt;

import java.util.Iterator;

/**
 * 反序列化修改器
 * 作用：类似序列化修改器，但作用于反序列化（JSON → Java 对象），找出需要混淆的字段，替换成自定义的反序列化器 ConfusionDeserializer。
 */
public class ConfusionDeserializerModifier extends BeanDeserializerModifier {

    @Override
    public BeanDeserializerBuilder updateBuilder(final DeserializationConfig config, final BeanDescription beanDescription, final BeanDeserializerBuilder builder) {
        Iterator it = builder.getProperties();

        while (it.hasNext()) {
            SettableBeanProperty p = (SettableBeanProperty) it.next();
            if ((null != p.getAnnotation(IdEncrypt.class)||p.getName().equalsIgnoreCase("id"))) {
                JsonDeserializer<Object> current = p.getValueDeserializer();
                // 找到需要混淆的字段，替换成自定义反序列化器
                builder.addOrReplaceProperty(p.withValueDeserializer(new ConfusionDeserializer(p.getValueDeserializer(),p.getType())), true);
            }
        }
        return builder;
    }
}
