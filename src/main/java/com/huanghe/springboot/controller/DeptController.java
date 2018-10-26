package com.huanghe.springboot.controller;

import com.huanghe.springboot.bean.Department;
import com.huanghe.springboot.bean.Employee;
import com.huanghe.springboot.mapper.DepartmentMapper;
import com.huanghe.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: River
 * @Date:Created in  12:01 2018/10/24
 * @Description:
 */
@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id) {
        Department department = departmentMapper.getDeptById(id);
        return department;
    }

    @GetMapping("/dept")
    public Department insetDept(Department department) {
        departmentMapper.insertDept(department);
        return department;
    }

}
