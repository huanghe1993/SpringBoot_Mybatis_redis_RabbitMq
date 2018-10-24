package com.huanghe.springboot.mapper;

import com.huanghe.springboot.bean.Employee;

/**
 * @Author: River
 * @Date:Created in  15:48 2018/10/24
 * @Description:
 *
 * //@Mapper或者是@MapperScan的方式将接口扫描装配到容器中
 */

public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void inserEmp(Employee employee);
}
