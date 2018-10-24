package com.huanghe.springboot.mapper;

import com.huanghe.springboot.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * @Author: River
 * @Date:Created in  11:56 2018/10/24
 * @Description:
 */

/**
 * @Mapper :指定这个是操作数据库的mapper
 */
@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Department department);

}
