package com.huanghe.springboot.service;

import com.huanghe.springboot.bean.Employee;
import com.huanghe.springboot.mapper.EmployeeMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Author: River
 * @Date:Created in  17:46 2018/10/24
 * @Description: //@CacheConfig(cacheNames="emp")放在类上可以指定全局的cache的名称，抽取缓存的公共的配置
 */

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 从缓存中获取数据
     * <p>
     * CacheManager管理多个Cache组件的，对缓存真正的CRUD的操作是在Cache组件中，每一个缓存组件有自己的唯一的一个名字
     * 几个属性：
     * cacheNames/value:缓存组件的名称,将方法的缓存放在哪个缓存中，是数组的形式，可以指定多个缓存
     * key:缓存数据的时候使用的key,默认是使用的是方法参数的值；
     * 可以使用SPEL表达式 “#id”：取出id的值作为key,等同于”#a0“,"",#p0"
     * keyGenerator:key的生成器；可以自己指定生成器的id
     * key和keyGenerator二选一的使用
     * cacheManager/cacheResolver(二选一):缓存管理器，可以自己进行指定缓存管理器
     * condition：指定符合条件的情况下才会缓存，eg:condition="#id>1"当id的值大于1的时候才进行缓存
     * unless:否定缓存，当unless指定的条件为true的时候就不会进行缓存，比如unless ="#result == null"当结果为nulL的时候就不缓存
     * sync:是否使用异步的形式
     * <p>
     * 原理：
     * 1、自动配置类：CacheAutoConfiguration.java
     * 2、缓存的配置类：
     * org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
     * org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
     * 3、那个配置类默认是生效的呢
     * SimpleCacheConfiguration：给容器中注册了一个CacheManager:ConcurrentMapCacheManager
     *
     * @param id
     * @return
     */
    @Cacheable(value = "emp")
    public Employee getEmp(Integer id) {
        Employee employee = employeeMapper.getEmpById(id);
        return employee;
    }

    /**
     * 即调用方法，又更新缓存
     * <p>
     * 先调用方法，然后把方法的结果放到缓存中
     * 运行时机：
     * 1：目标方法
     * 2：将结果放到缓存中
     */
    @CachePut(value = "emp", key = "#employee.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("update emp" + employee);
        employeeMapper.updateEmp(employee);
        return employee;

    }


    /**
     * @CacheEvit:缓存清除 key：指定清除的数据
     * allEntry =true:删除所有的缓存
     * beforeInvocation：默认为false是否在方法之前执行，出现异常就不会清除缓存
     */
    @CacheEvict(value = "emp", key = "#id")
    public void deleteEmp(Integer id) {
        System.out.println("deleteEmp:" + id);
        employeeMapper.deleteEmp(id);
    }

    /**
     * 使用@Caching组合注解
     * lastName、id、email都在缓存中存在
     */
  /*  @Caching(
            cacheable = {
                    @Cacheable(value = "emp", key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp", key = "#result.id"),
                    @CachePut(value = "emp",key ="#result.email")
            }
    )*/
    @Cacheable(value = "emp")
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }

    /**
     * 监听来自消息队列中的内容@RabbitListener("")里面的是参数是数组的方式，队列的名称
     * <p>
     * 只要是消息对列里面有内容就会收到消息
     */
    @RabbitListener(queues = "atguigu.news")
    public void receive(Employee employee) {
        System.out.println("收到消息：" + employee);
    }

    /**
     * 测试消息头
     */
    @RabbitListener(queues = "atguigu")
    public void receiver02(Message message) {
        //消息的内容
        System.out.println(message.getBody());
        //消息头
        System.out.println(message.getMessageProperties());
    }
}

