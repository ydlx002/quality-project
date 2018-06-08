package com.gxjtkyy.quality.dao;

import com.gxjtkyy.quality.domain.dto.LikeDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;

/**
 * 点赞DAO
 * @Package com.gxjtkyy.quality.dao
 * @Author lizhenhua
 * @Date 2018/6/4 15:39
 */
@Mapper
public interface LikeMapper {

    /**
     * 点赞
     * @param dto
     * @return
     */
    @Insert("insert into t_likes values(0, #{targetType}, #{targetId}, #{userId})")
    boolean insert(LikeDTO dto);


    /**
     * 取消点赞
     * @param id
     * @param userId
     * @return
     */
    @Delete("delete from t_likes where id = #{id} and user_id = #{userId}")
    boolean delete(@Param("id") Integer id, @Param("userId") String userId);


    /**
     * 判断是否已存在
     * @param targetId
     * @param targetType
     * @param userId
     * @return
     */
    @Select("select count(1) from t_likes where target_id = #{targetId} and target_type = #{targetType} and user_id = #{userId}")
    int isExists(@Param("targetId") Integer targetId, @Param("targetType") Integer targetType,@Param("userId") String userId );


}
