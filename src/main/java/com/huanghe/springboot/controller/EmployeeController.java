package com.huanghe.springboot.controller;

import com.huanghe.springboot.bean.Employee;
import com.huanghe.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: River
 * @Date:Created in  9:47 2018/10/25
 * @Description: 测试缓存的使用
 */
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        if (id instanceof Integer) {
            System.out.println("true");
        }
        Employee emp = employeeService.getEmp( id);
        return emp;
    }

    @GetMapping("/emp")
    public Employee updateEmp(Employee employee) {
        Employee updateEmp = employeeService.updateEmp(employee);
        return updateEmp;
    }

    @GetMapping("/delete")
    public String deleteEmp(Integer id) {
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/empt/lastName/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName) {
        Employee employee = employeeService.getEmpByLastName(lastName);
        return employee;
    }
}
