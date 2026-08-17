package com.heima.common.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置入口
 * 在 Spring Boot 启动时，把自定义的 Jackson 模块注册到全局 ObjectMapper
 * 所有接口的 JSON 序列化/反序列化都会经过这个模块
 */
@Configuration
public class InitJacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper = ConfusionModule.registerModule(objectMapper);
        return objectMapper;
    }

}
