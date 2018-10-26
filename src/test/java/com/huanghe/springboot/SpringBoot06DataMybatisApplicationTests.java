package com.huanghe.springboot;

import com.huanghe.springboot.bean.Employee;
import com.huanghe.springboot.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot06DataMybatisApplicationTests {

	@Autowired
	EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 测试mapper，getEmpById(int i)
     */
	@Test
	public void contextLoads() {
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee.getLastName());
    }

    /**
     * Redis
     * 测试String list  set Hash
     * stringRedisTemplate.opsForValue():简化操作字符串的
     */
    @Test
    public void test01(){
        stringRedisTemplate.opsForValue().append("msg", "hello");
    }

    /**
     * 测试保存对象
     */
    @Test
    public void test02() {
        Employee employee = employeeMapper.getEmpById(1);
        //默认保存对象，是使用的是JDk的序列化机制，序列化后数据保存到redis中
        redisTemplate.opsForValue().set("emp-01",employee);
        //将数据以json的数据保存
    }

}
