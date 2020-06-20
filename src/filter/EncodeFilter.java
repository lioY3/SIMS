package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodeFilter implements Filter {

    public EncodeFilter() {
        
    }

	public void destroy() {
		
	}

	/*
	 * public void doFilter(ServletRequest request, ServletResponse response,
	 * FilterChain chain) throws IOException, ServletException {
	 * request.setCharacterEncoding("UTF-8"); chain.doFilter(request, response);
	 * 
	 * }
	 */

	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		//设置编码格式
		String a = request.getRequestURI();
        if(a.contains(".css") || a.contains(".js") || a.contains(".png")|| a.contains(".jpg")){
            //如果发现是css或者js文件，直接放行
        	chain.doFilter(request, response);
        }
        else {
        	request.setCharacterEncoding("utf-8");
        	response.setContentType("text/html; charset=utf-8");
        	
        }
        chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
