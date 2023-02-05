package cn.com.hyun.framework.web.aop;

import org.apache.commons.codec.CharEncoding;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hyunwoo
 */
public class CharacterEncodingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding(CharEncoding.UTF_8);
        chain.doFilter(request, response);
        response.setCharacterEncoding(CharEncoding.UTF_8);
    }
}
