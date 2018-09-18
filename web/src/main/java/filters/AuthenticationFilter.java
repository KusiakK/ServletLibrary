package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/adminPanel.jsp")
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
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
