package servlets;

import org.junit.Test;
import org.mockito.Mockito;
import utility.ServletUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.mockito.Mockito.*;

public class LoginServletTest {

    private HttpServletRequest req = mock(HttpServletRequest.class);
    private HttpServletResponse resp = mock(HttpServletResponse.class);
    private RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    private HttpSession session = mock(HttpSession.class);

    @Test
    public void testLoginServletDoPostForSuccessfulLogin() throws Exception {

        String login = "correctLogin";
        String password = "correctPassword";

        when(req.getParameter("login")).thenReturn(login);
        when(req.getParameter("password")).thenReturn(password);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(resp.getWriter()).thenReturn(writer);


        when(req.getSession(true)).thenReturn(session);
        doNothing().when(session).setAttribute("login", login);

        doNothing().when(req).setAttribute("success", "You have been logged in!");

        when(req.getRequestDispatcher("/home")).thenReturn(dispatcher);
        doNothing().when(dispatcher).forward(req, resp);


        new LoginServlet().doPost(req, resp);

        verify(req, atLeastOnce()).getParameter("login");
        verify(req, atLeastOnce()).getParameter("password");
        verify(req, atLeastOnce()).getSession(true);
        verify(session, atLeastOnce()).setAttribute("login", login);
        verify(req, atLeastOnce()).getRequestDispatcher("/home");
        verify(dispatcher, atLeastOnce()).forward(req, resp);

    }
}