package servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class LoginServletTest {

    private HttpServletRequest req = mock(HttpServletRequest.class);
    private HttpServletResponse resp = mock(HttpServletResponse.class);
    private RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    private HttpSession session = mock(HttpSession.class);

    @Test
    public void testLoginServletDoPostForSuccessfulLogin() throws Exception {
        String correctLogin = "correctLogin";
        String correctPassword = "correctPassword";
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(req.getParameter("login")).thenReturn(correctLogin);
        when(req.getParameter("password")).thenReturn(correctPassword);
        when(resp.getWriter()).thenReturn(writer);
        when(req.getSession(true)).thenReturn(session);
        doNothing().when(session).setAttribute("login", correctLogin);
        doNothing().when(req).setAttribute("success", "You have been logged in!");
        when(req.getRequestDispatcher("/home")).thenReturn(dispatcher);
        doNothing().when(dispatcher).forward(req, resp);

        new LoginServlet().doPost(req, resp);

        verify(req, atLeastOnce()).getParameter("login");
        verify(req, atLeastOnce()).getParameter("password");
        verify(req, atLeastOnce()).getSession(true);
        verify(session, atLeastOnce()).setAttribute("login", correctLogin);
        verify(req, atLeastOnce()).getRequestDispatcher("/home");
        verify(dispatcher, atLeastOnce()).forward(req, resp);
    }

    @Test
    public void testIfCannotLoginWithIncorrectData() throws Exception {
        String incorrectLogin = "incorrect login";
        String incorrectPassword = "incorrect password";
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(req.getParameter("login")).thenReturn(incorrectLogin);
        when(req.getParameter("password")).thenReturn(incorrectPassword);
        when(resp.getWriter()).thenReturn(writer);
        when(req.getSession(true)).thenReturn(session);
        doNothing().when(session).setAttribute("login", incorrectLogin);
        doNothing().when(req).setAttribute("success", "You have been logged in!");
        when(req.getRequestDispatcher("login.jsp")).thenReturn(dispatcher);
        doNothing().when(dispatcher).forward(req, resp);

        new LoginServlet().doPost(req, resp);

        verify(req, atLeastOnce()).getParameter("login");
        verify(req, atLeastOnce()).getParameter("password");
        verify(req, never()).getSession(true);
        verify(session, never()).setAttribute("login", incorrectLogin);
        verify(req, atLeastOnce()).getRequestDispatcher("login.jsp");
        verify(dispatcher, atLeastOnce()).forward(req, resp);
    }
}