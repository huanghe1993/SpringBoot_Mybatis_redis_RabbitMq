package com.huanghe.springboot;

import com.huanghe.springboot.bean.Employee;
import com.huanghe.springboot.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: River
 * @Date:Created in  14:43 2018/10/26
 * @Description: 1:RabbitAutoConfiguration，自动配置类
 * 2:有自动配置了连接工厂ConnectionFactory
 * 3:RabbitTemplate:给RabbitMQ发送消息和接收消息
 * 4:AmqpAdmin:RabbitMQ系统管理功能组件：创建和删除Queue Exchange Binding
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    EmployeeService employeeService;

    /**
     * 1：单播（点对点）
     */
    @Test
    public void test01(){
        //meassgae可以自己定制消息头和消息体,交换器，陆游键，消息
        //rabbitTemplate.send(exchage,routekey,message);

        //object,默认是作为消息体的，只需要传入要发送的对象，自动的序列化发送给rabbitmq
        //rabbitTemplate.convertAndSend(exchage,routekey,object);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "第一条消息");
        map.put("data", Arrays.asList("hello", 123, true));
        //对象默认是被序列化之后发送出去的，默认使用的是JDK的方式
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", map);

    }

    /**
     * 测试接收消息,
     */
    @Test
    public void test02(){
        //获取消息队列的名称
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    /**
     * 测试广播
     */
    @Test
    public void sendMsg(){
        Employee emp = employeeService.getEmp(1);
        rabbitTemplate.convertAndSend("exchange.fanout","",emp);
    }

    /**
     * 测试创建Exchage 交换器
     */
    @Test
    public void createExchage() {
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchage"));
        System.out.println("创建完成");
    }

    /**
     * 测试创建队列
     */
    @Test
    public void createQueue(){
        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
    }

    /**
     * 测试绑定规则
     */
    @Test
    public void testBinding(){
        //声明一个绑定，第一个参数是目的地，绑定到哪个队列，第二个参数是绑定的类型（队列），第三个参数是路由器，第四个参数是绑定key“路由键”
        amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqpadmin.exchage", "amqp.haha", null));
    }

}
