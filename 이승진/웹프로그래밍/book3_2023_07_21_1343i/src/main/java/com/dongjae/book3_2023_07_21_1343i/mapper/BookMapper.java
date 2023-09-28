package com.dongjae.book3_2023_07_21_1343i.mapper;

import com.dongjae.book3_2023_07_21_1343i.dto.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("""
            select b.*, c.name categoryName
            from book b left join category c on b.categoryId = c.id
            """)
    List<Book> findAll();

    @Select("""
            select b.*, c.name categoryName
            from book b left join category c on b.categoryId = c.id
            where b.id = #{id}
            """)
    Book findById(int id);
}
