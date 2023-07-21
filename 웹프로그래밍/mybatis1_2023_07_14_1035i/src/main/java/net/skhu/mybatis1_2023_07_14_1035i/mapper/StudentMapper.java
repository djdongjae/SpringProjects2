package net.skhu.mybatis1_2023_07_14_1035i.mapper;

import net.skhu.mybatis1_2023_07_14_1035i.dto.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("""
            select s.*, d.name departmentName
            from student s join department d on s.departmentId = d.id
            """)
    List<Student> findAll();

    @Select("""
            select s.*, d.name departmentName
            from student s left join department d on s.departmentId = d.id
            where s.name like #{name}
            """)
    List<Student> findByName(String name);

    @Select("""
            select s.*, d.name departmentName
            from student s left join department d on s.departmentId = d.id
            where s.id = #{id}
            """)
    Student findById(int id);
}
