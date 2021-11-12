package mk.finki.ukim.mk.lab.web.filter;

import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class ColorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String color = (String) httpServletRequest.getSession().getAttribute("color");
        String path = httpServletRequest.getServletPath();
        if (color != null || path.contains("/balloons"))
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        else httpServletResponse.sendRedirect("/balloons");
    }

    @Override
    public void destroy() {

    }
}
