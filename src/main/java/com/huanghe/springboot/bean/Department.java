package com.huanghe.springboot.bean;

/**
 * @Author: River
 * @Date:Created in  11:54 2018/10/24
 * @Description:
 */

public class Department {

    private Integer id;
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
