package com.gxjtkyy.quality.dao;

import com.gxjtkyy.quality.domain.vo.ColDictVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 栏目dao
 * @Package com.gxjtkyy.quality.dao
 * @Author lizhenhua
 * @Date 2018/6/4 12:36
 */
@Mapper
public interface ColumnMapper {

    @Select("select id as colId, col_name as colName from t_column")
    List<ColDictVO> getColDict();

    @Select("select count(1) from t_column where id = #{id}")
    int countById(@Param("id") Integer id);
}
