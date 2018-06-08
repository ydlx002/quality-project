package com.gxjtkyy.quality.dao;

import com.gxjtkyy.quality.domain.dto.AuditDTO;
import com.gxjtkyy.quality.domain.dto.CommentDTO;
import com.gxjtkyy.quality.domain.dto.CondictionDTO;
import com.gxjtkyy.quality.domain.vo.CommentVO;
import lombok.Setter;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 评论DAO
 * @Package com.gxjtkyy.quality.dao
 * @Author lizhenhua
 * @Date 2018/5/31 12:29
 */
@Mapper
public interface CommentMapper {

    /**
     * 写入评论
     * @param dto 评论数据传输对象
     * @return
     */
    @Insert("insert into t_comment(parent_id, article_id,user_id,content,create_time, status, ip)values " +
            "(#{parentId}, #{articleId}, #{userId}, #{content}, #{createTime}, #{status}, #{ip})")
    boolean insert(CommentDTO dto);


    /**
     * 根据评论ID获取评论详情
     * @param commentId
     * @return
     */
    @Select("select a.id as id, a.parent_id as parentId, a.article_id as articleId, a.user_id as userId, b.user_name as userName," +
            "b.user_type as userType, a.content as content, a.create_time as createTime, " +
            "a.status as status ,a.ip as ip from t_comment a left join t_user b on a.user_id= b.user_id where id = #{commentId}")
    CommentDTO getComment(Integer commentId);


    /**
     * 根据文章ID获取评论内容
     * @param articleId 文章ID
     * @return
     */
    @Select("select a.id as id, a.parent_id as parentId, a.article_id as articleId, a.user_id as userId, b.user_name as userName," +
            "b.user_type as userType, a.content as content, a.create_time as createTime, " +
            "a.status as status ,a.ip as ip from t_comment a left join t_user b on a.user_id= b.user_id where a.article_id = #{articleId}" +
            " and a.status = #{status}")
    List<CommentDTO> getList(@Param("articleId") Integer articleId, @Param("status") Integer status);


    /**
     * 更新评论状态
     * @param dto
     * @return
     */
    @Update("update t_comment set status = #{status}, auditor_id = #{auditorId},audit_time=#{auditTime},audit_opinion=#{auditOpinion} where id = #{id} ")
    boolean updateStatus(AuditDTO dto);


    /**
     * 分页获取评论审核列表
     * @param dto
     * @return
     */
    @Select(" <script>     select " +
            " a.id as commentId, b.user_name as userName, b.user_type as userType, a.content as content, a.ip as ip, a.create_time as createTime, a.status as status, " +
            " c.user_name as auditor, a.audit_time as auditTime, a.audit_opinion as auditOpinion " +
            " from t_comment a left join t_user b on a.user_id = b.user_id left join t_user c on a.auditor_id = c.user_id "+
            "  where 1=1 " +
            " <if test=\"dto != null and dto.status != null\"> " +
            "        AND a.status = #{dto.status} " +
            "</if>" +
            "  order by a.id DESC limit #{start}, #{pageSize}" +
            "</script>")
    List<CommentVO> getListByPage(CondictionDTO dto);


    /**
     * 分页获取评论审核列表
     * @param dto
     * @return
     */
    @Select(" <script>     select  count(1) " +
            " from t_comment a left join t_user b on a.user_id = b.user_id left join t_user c on a.auditor_id = c.user_id "+
            "  where 1=1 " +
            " <if test=\"dto != null and dto.status != null\"> " +
            "        AND a.status = #{dto.status} " +
            "</if>" +
            "  order by a.id DESC limit #{start}, #{pageSize}" +
            "</script>")
    int countComment(CondictionDTO dto);


    /**
     * 点赞数+1
     * @param id
     * @return
     */
    @Update("update t_comment set like_count=like_count +1 where id = #{id}")
    boolean addLikeCount(@Param("id") Integer id);

}
