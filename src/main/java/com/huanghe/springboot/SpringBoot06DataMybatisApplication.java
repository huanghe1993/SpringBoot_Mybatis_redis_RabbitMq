package com.huanghe.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: River
 * 、@MapperScan("com.huanghe.springboot.mapper") :开启mappers扫描
 * 、@EnableCaching：开启缓存配置，默认使用的是ConcurrentMapCacheManager
 * 、在开发的时候使用的是缓存的中间件，redis,memcached
 * 、@EnableRabbit开启基于注解的RabbitMQ
 */

@SpringBootApplication
@MapperScan("com.huanghe.springboot.mapper")
@EnableCaching
@EnableRabbit
@EnableAsync
@EnableScheduling
public class SpringBoot06DataMybatisApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(SpringBoot06DataMybatisApplication.class, args);
	}
}
