package cn.luliangwei.web.development.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.luliangwei.web.development.beans.User;
import cn.luliangwei.web.development.http.HttpServletRequestWrapper;

/**
 * 
 * web Filter 过滤器.
 * </p>
 *
 * @author luliangwei
 * @since 0.0.1
 */
@Configuration
public class ModifyRequestBodyFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request);
        byte[] rawData = wrapper.getRawData();
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(rawData, User.class);
        user.setAge(22);
        user.setName("KOBE");
        
        wrapper.setRawData(mapper.writeValueAsBytes(user));
        filterChain.doFilter(wrapper, response);
    }

}
