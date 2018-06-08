package com.gxjtkyy.quality.dao;

import com.gxjtkyy.quality.domain.BriefArticle;
import com.gxjtkyy.quality.domain.dto.ArticleDTO;
import com.gxjtkyy.quality.domain.dto.AuditDTO;
import com.gxjtkyy.quality.domain.dto.CondictionDTO;
import com.gxjtkyy.quality.domain.vo.ArticleAuditVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 文章DAO
 * @Package com.gxjtkyy.quality.dao
 * @Author lizhenhua
 * @Date 2018/5/31 10:00
 */
@Mapper
public interface ArticleMapper {

    /**
     * 统计栏目文章数
     * @param colId 栏目ID
     * @param status 状态
     * @return
     */
    @Select("select count(1) from t_article where col_id = #{colId} and status = #{status}")
    int count(@Param("colId") Integer colId, @Param("status") Integer status);

    /**
     * 分页查询栏目摘要列表
     * @param colId 栏目ID
     * @param status 状态
     * @param start 分页开始
     * @param pageSize 页面大小
     * @return
     */
    @Select("select a.id as articleId, a.title as articleTitle, a.summary as summary, a.comment_count as commentCount, a.like_count as likeCount from t_article a " +
            "where col_id = #{colId} and status = #{status} order by create_time desc limit #{start}, #{pageSize}")
    List<BriefArticle> getBriefList(@Param("colId") Integer colId, @Param("status") Integer status, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    /**
     * 根据文章ID查询文章
     * @param id 文章ID
     * @return
     */
    @Select("select a.id as id, a.title as title, a.summary as summary, a.content as content, a.author_id as authorId, b.user_name as author, " +
            "a.create_time as createTime, a.source as source ,a.view_count as viewCount, a.status as status from t_article a left join t_user b on a.author_id = b.user_id " +
            " where a.id = #{id}")
    ArticleDTO getById(@Param("id") Integer id);


    /**
     * 新增文章
     * @param dto
     * @return
     */
    @Insert("insert into t_article(col_id,title,summary,content,author_id,create_time,source)" +
            " values (#{colId}, #{title}, #{summary}, #{content}, #{authorId},#{createTime}, #{source})")
    boolean insert(ArticleDTO dto);


    /**
     * 更新文章状态
     * @param id 文章ID
     * @param status 状态
     * @return
     */
    @Update("update t_article set status = #{status}, auditor_id = #{auditorId},audit_time=#{auditTime},audit_opinion=#{auditOpinion} where id = #{id}")
    boolean updateStatus(AuditDTO dto);


    /**
     * 评论数加1
     * @param id
     * @return
     */
    @Update("update t_article set comment_count = comment_count+1 where id = #{id}")
    boolean addCommentCount(@Param("id") Integer id);


    /**
     * 阅读量+1
     * @param id
     * @return
     */
    @Update("update t_article set view_count = view_count+1 where id = #{id}")
    boolean addViewCount(@Param("id") Integer id);

    /**
     * 点赞数+1
     * @param id
     * @return
     */
    @Update("update t_article set like_count = like_count+1 where id = #{id}")
    boolean addLikeCount(@Param("id") Integer id);

    /**
     * 分页查询文章列表for审核
     * @param dto
     * @return
     */
    @Select(" <script>     select " +
            " a.id as id, b.col_name as colName, a.title as title, c.user_name as author, a.create_time as createTime, a.status as status, " +
            " a.source as source,d.user_name as auditor, a.audit_time as auditTime, a.audit_opinion as auditOpinion " +
            " from t_article a left join t_column b on a.col_id = b.id left join t_user c on a.author_id = c.user_id left join " +
            " t_user d on a.auditor_id = d.user_id " +
            "  where 1=1 " +
            " <if test=\"dto != null and dto.status != null\"> " +
            "        AND a.status = #{dto.status} " +
            "</if>" +
            " <if test=\"dto != null and dto.colId != null\"> " +
            "        AND a.col_id = #{dto.colId} " +
            "</if>" +
            "<if test=\"dto != null and dto.title != null and !&quot;&quot;.equals(dto.title.trim())\"> " +
            "        AND a.title like CONCAT('%', #{dto.title}, '%') " +
            "</if>" +
            "  order by a.id DESC limit #{start}, #{pageSize}" +
            "</script>")
    List<ArticleAuditVO> getAuditList(CondictionDTO dto);



    /**
     * 统计栏目文章数
     * @param colId 栏目ID
     * @param status 状态
     * @return
     */
    @Select(" <script>     select  count(1) "+
            " from t_article a left join t_column b on a.col_id = b.id left join t_user c on a.author_id = c.user_id left join " +
            " t_user d on a.auditor_id = d.user_id " +
            "  where 1=1 " +
            " <if test=\"dto != null and dto.status != null\"> " +
            "        AND a.status = #{dto.status} " +
            "</if>" +
            " <if test=\"dto != null and dto.colId != null\"> " +
            "        AND a.col_id = #{dto.colId} " +
            "</if>" +
            "<if test=\"dto != null and dto.title != null and !&quot;&quot;.equals(dto.title.trim())\"> " +
            "        AND a.title like CONCAT('%', #{dto.title}, '%') " +
            "</if>" +
            "</script>")
    int countArticle(CondictionDTO dto);


}
