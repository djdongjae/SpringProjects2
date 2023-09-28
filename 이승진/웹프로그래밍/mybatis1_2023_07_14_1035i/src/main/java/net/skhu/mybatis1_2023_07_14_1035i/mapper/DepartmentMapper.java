package net.skhu.mybatis1_2023_07_14_1035i.mapper;

import net.skhu.mybatis1_2023_07_14_1035i.dto.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    @Select("""
            select *
            from department;
            """)
    List<Department> findAll();
}
