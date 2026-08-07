package com.heima.wemedia.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 包的扫描，让整个模块可以扫描到apis模块里 IArticleClient远程feign接口的 “熔断兜底类”
 */
@Configuration
@ComponentScan("com.heima.apis.article.fallback")
public class InitConfig {
}