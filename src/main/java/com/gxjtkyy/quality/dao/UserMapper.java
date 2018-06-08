package com.gxjtkyy.quality.dao;

import com.gxjtkyy.quality.domain.dto.CondictionDTO;
import com.gxjtkyy.quality.domain.dto.UserDTO;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Package com.gxjtkyy.quality.dao
 * @Author lizhenhua
 * @Date 2018/6/1 12:44
 */
@Mapper
public interface UserMapper {

    /**
     * 根据账号密码查询用户
     * @param userAccount 用户账号
     * @param password 密码
     * @return
     */
    @Select("select user_id as userId, user_account as userAccount, user_type as userType, user_name " +
            "as userName, mobile as mobile, department as department, position as position,remark, email, " +
            "create_time as createTime from t_user where user_account = #{userAccount} and password = #{password}")
    UserDTO getUser(@Param("userAccount") String userAccount, @Param("password") String password);


    /**
     * 分页获取用户列表
     * @param dto
     * @return
     */
    List<UserDTO> getListByPage(CondictionDTO dto);

    /**
     *  获取用户总数
     * @param dto
     * @return
     */
    int getTotalCount(CondictionDTO dto);


    /**
     * 写入用户信息
     * @param userDTO
     * @return
     */
    boolean insertUser(UserDTO userDTO);


    /***
     * 更新用户信息
     * @param userDTO
     * @return
     */
    boolean updateUser(UserDTO userDTO);

    /**
     * 根据账号获取个数
     * @param userAccount
     * @return
     */
    @Select("select count(1) from t_user where user_account = #{userAccount}")
    int getCount(@Param("userAccount") String userAccount);


    /**
     * 根据用户ID查询用户
     * @param userId 用户ID
     * @return
     */
    @Select("select user_id as userId, user_account as userAccount, user_type as userType, user_name " +
            "as userName, mobile as mobile, department as department, position as position,remark,email, " +
            "create_time as createTime from t_user where user_ID = #{userId}")
    UserDTO getUserById(@Param("userId") String userId);
}
