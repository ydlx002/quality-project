package com.gxjtkyy.quality.service.impl;

import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.dao.UserMapper;
import com.gxjtkyy.quality.domain.UserInfo;
import com.gxjtkyy.quality.domain.dto.CommentDTO;
import com.gxjtkyy.quality.domain.dto.CondictionDTO;
import com.gxjtkyy.quality.domain.dto.UserDTO;
import com.gxjtkyy.quality.domain.vo.CommentVO;
import com.gxjtkyy.quality.domain.vo.PageResultVO;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.domain.vo.UserVO;
import com.gxjtkyy.quality.domain.vo.request.QueryUserPageRequest;
import com.gxjtkyy.quality.domain.vo.request.RegistRequest;
import com.gxjtkyy.quality.domain.vo.request.UpdateUserRequest;
import com.gxjtkyy.quality.exception.SystemException;
import com.gxjtkyy.quality.service.UserService;
import com.gxjtkyy.quality.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户管理服务实现
 * @Package com.gxjtkyy.quality.service.impl
 * @Author lizhenhua
 * @Date 2018/6/1 12:57
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;


    @Override
    public ResponseVO addUser(RegistRequest request) {
        if(userMapper.getCount(request.getUserAccount()) > 0){
            return new ResponseVO(ResultType.ACCOUNT_EXISTS);
        }
        UserDTO dto = new UserDTO();
        dto.setUserId(UUID.randomUUID().toString().replace("-",""));
        dto.setCreateTime(new Date());
        dto.setUserType(0);
        dto.setUserName(request.getUserName());
        dto.setDepartment(request.getDepartment());
        dto.setPosition(request.getPosition());
        dto.setUserAccount(request.getUserAccount());
        dto.setMobile(request.getMobile());
        dto.setPassword(MD5Util.passwordEncoder(request.getPassword()));
        dto.setEmail(request.getEmail());
        if(userMapper.insertUser(dto)){
            return new ResponseVO();
        }else{
            return new ResponseVO(ResultType.FAIL);
        }
    }

    @Override
    public ResponseVO updateUser(UpdateUserRequest request, UserInfo userInfo) {
        ResponseVO response = new ResponseVO();
        try {
            if(null == userMapper.getUserById(request.getUserId())){
                throw new SystemException(ResultType.ACCOUNT_NO_EXISTS);
            }
            UserDTO dto = new UserDTO();
            dto.setDepartment(request.getDepartment());
            dto.setPosition(request.getPosition());
            dto.setMobile(request.getMobile());
            if(null != request.getUserType()){
                if (null ==userInfo || userInfo.getUserType() <= 2) {
                    throw new SystemException(ResultType.PERMISSION_DENY);
                }
                dto.setUserType(request.getUserType());
            }
            dto.setUserId(request.getUserId());
            if(!userMapper.updateUser(dto)){
                return response.invoke(ResultType.FAIL);
            }
        } catch (SystemException e) {
            log.error("更改用户信息",e);
            response.invoke(e);
        }
        return response;
    }

    @Override
    public ResponseVO getUserByPage(QueryUserPageRequest request, UserInfo userInfo) {
        ResponseVO response = new ResponseVO();
        try {
            if (userInfo.getUserType() <= 2) {
                throw new SystemException(ResultType.PERMISSION_DENY);
            }
            int currentPage = request.getCurrentPage();
            int pageSize = request.getPageSize();
            if (currentPage < 0) {
                throw new SystemException(ResultType.INVALID_CURRENT_PAGE);
            }
            if (pageSize < 0) {
                throw new SystemException(ResultType.INVALID_PAGESIZE);
            }
            UserDTO dto = new UserDTO();
            dto.setUserName(request.getUserName());
            dto.setUserType(request.getUserType());
            CondictionDTO condiction = new CondictionDTO();
            condiction.setStart((currentPage - 1) * pageSize);
            condiction.setPageSize(pageSize);
            condiction.setDto(dto);
            int count = userMapper.getTotalCount(condiction);
            List<UserDTO> list = userMapper.getListByPage(condiction);
            List<UserVO> userVOS = new ArrayList<>();
            for(UserDTO userDTO : list) {
                UserVO vo = new UserVO();
                BeanUtils.copyProperties(userDTO, vo);
                userVOS.add(vo);
            }
            PageResultVO<UserVO> page = new PageResultVO<>();
            page.setTotalElements(count);
            page.setCurrentPage(currentPage);
            page.setContent(userVOS);
            response.setData(page);
        } catch (SystemException e) {
            response.invoke(e);
            log.error("获取用户列表异常", e);
        }
        return response;
    }
}
