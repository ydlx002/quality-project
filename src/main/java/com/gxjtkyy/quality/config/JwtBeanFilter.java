package com.gxjtkyy.quality.config;

import com.alibaba.fastjson.JSON;
import com.gxjtkyy.quality.constants.QuaqlityConstant;
import com.gxjtkyy.quality.constants.ResultType;
import com.gxjtkyy.quality.domain.vo.ResponseVO;
import com.gxjtkyy.quality.exception.SystemException;
import com.gxjtkyy.quality.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Constants;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.security.auth.login.LoginException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Package com.gxjtkyy.quality.config
 * @Author lizhenhua
 * @Date 2018/6/1 10:46
 */
public class JwtBeanFilter extends GenericFilterBean {

    @Autowired
    private Audience audience;

    /**
     * Reserved claims（保留），它的含义就像是编程语言的保留字一样，属于JWT标准里面规定的一些claim。JWT标准里面定好的claim有：
     * <p>
     * iss(Issuser)：代表这个JWT的签发主体；
     * sub(Subject)：代表这个JWT的主体，即它的所有人；
     * aud(Audience)：代表这个JWT的接收对象；
     * exp(Expiration time)：是一个时间戳，代表这个JWT的过期时间；
     * nbf(Not Before)：是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的；
     * iat(Issued at)：是一个时间戳，代表这个JWT的签发时间；
     * jti(JWT ID)：是JWT的唯一标识。
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        //等到请求头信息authorization信息
        final String authHeader = request.getHeader("authorization");
        try {
            if (authHeader == null || !authHeader.startsWith("bearer;")) {
                throw new SystemException(ResultType.INVALIDTOKEN);
            }
            final String token = authHeader.substring(7);
            if (audience == null) {
                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                audience = (Audience) factory.getBean("audience");
            }
            final Claims claims = JwtUtil.parseJWT(token, audience.getBase64Secret());
            if (claims == null) {
                throw new SystemException(ResultType.INVALIDTOKEN);
            }
            request.setAttribute(QuaqlityConstant.CLAIMS, claims);
            chain.doFilter(req, res);
        } catch (SystemException e) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ResponseVO vo = new ResponseVO();
            vo.setCode(e.getCode());
            vo.setMsg(e.getMsg());
            response.getWriter().write(JSON.toJSONString(vo));
        }
    }

}
