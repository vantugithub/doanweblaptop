package filter;

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
import javax.servlet.http.HttpSession;

import BEAN.MyUser;
import BEAN.Role;

@WebFilter("/UserRoleFilter")
public class UserRoleFilter implements Filter {

    public UserRoleFilter() 
    {
    	
    }

	public void destroy()
	{
		
	}
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        HttpSession session = request.getSession(false);
        String loginUrl = request.getContextPath()+"/Login";
        String roleName = "";
        if(session.getAttribute("USERMODEL")!=null) {
        	MyUser myUser = (MyUser) session.getAttribute("USERMODEL");
            Role role = myUser.getRole();
            roleName = role.getRoleName();
        }
        
        if( session.getAttribute("USERMODEL")==null ) {
        	response.sendRedirect(loginUrl);
        }
        else if(roleName.equals("ROLE_USER") && request.getServletPath().contains("admin")==false 
        		&& request.getServletPath().contains("manager")==false) {
        	chain.doFilter(request, response);
        }
        else {
        	if(roleName.equals("ROLE_ADMIN") && request.getServletPath().contains("admin")) {
        		chain.doFilter(request, response);
        	}
        	else if(roleName.equals("ROLE_MANAGER") && request.getServletPath().contains("manager")) {
        		chain.doFilter(request, response);
        	}
        	else {
        		response.sendRedirect(loginUrl);
        	}
        }
	}

	public void init(FilterConfig fConfig) throws ServletException
	{
		
	}

}
