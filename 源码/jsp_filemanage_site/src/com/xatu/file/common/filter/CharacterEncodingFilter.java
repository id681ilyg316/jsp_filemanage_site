package com.xatu.file.common.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CharacterEncodingFilter
 */
@WebFilter("/"
		+ ""
		+ "CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter {

	
    /**
     * Default constructor. 
     */
    public CharacterEncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

		System.out.println("********destory********");
	}
	private String  CharacterEncoding=null;
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		
		System.out.println("********init********");
		//              ��˫����Ϊ����������˫����Ϊ����
		CharacterEncoding = fConfig.getInitParameter("CharEncoding");
		if(CharacterEncoding==null){
			CharacterEncoding="UTF-8";
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		System.out.println("*******doFilter********");
		//��servlet����ת����httpservlet����
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		//�����ַ���
//		req.setCharacterEncoding("UTF-8");
//		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding(CharacterEncoding);
		resp.setCharacterEncoding(CharacterEncoding);
		//�����󽻸���һ������������������Դ����
		chain.doFilter(request, response);
	}

}

