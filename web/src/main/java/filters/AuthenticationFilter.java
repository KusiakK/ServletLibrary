package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/browseOption")
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }
    //TODO fix filter
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        if (null != req.getParameter("actionType") && "showBook".equals(req.getParameter("actionType"))){
            chain.doFilter(req, resp);
        }
        if (null != httpReq.getSession(false) && null != httpReq.getSession(false).getAttribute("userName")) {
            chain.doFilter(req, resp);
        } else {
            req.setAttribute("errorHead", "You have to log in first! ");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
