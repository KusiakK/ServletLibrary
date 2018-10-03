package filters;

import utility.ServletUtility;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/bookOption")
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        if (null != req.getParameter("actionType") && "showBook".equals(req.getParameter("actionType"))) {
            chain.doFilter(req, resp);
        } else if (null != httpReq.getSession(false) && null != httpReq.getSession(false).getAttribute("login")) {
            chain.doFilter(req, resp);
        } else {
            req.setAttribute(ServletUtility.SINGLE_ERROR_ATTRIBUTE, "You have to log in first! ");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
