package servlets;

import models.User;
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

    @Test
    public void testLoginServletDoPostForSuccessfulLogin() throws Exception {
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);

        User user = new User();
        user.setLogin("correctLogin");
        user.setPassword("correctPassword");

        when(req.getParameter("login")).thenReturn(user.getLogin());
        when(req.getParameter("password")).thenReturn(user.getPassword());

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(resp.getWriter()).thenReturn(writer);


        when(req.getSession(true)).thenReturn(session);
        doNothing().when(session).setAttribute("login", user.getLogin());

        doNothing().when(req).setAttribute("success", "You have been logged in!");

        when(req.getRequestDispatcher("/home")).thenReturn(dispatcher);
        doNothing().when(dispatcher).forward(req, resp);

        new LoginServlet().doPost(req, resp);

        verify(req, atLeastOnce()).getParameter("login");
        verify(req, atLeastOnce()).getParameter("password");
        verify(req, atLeastOnce()).getSession(true);
        verify(session, atLeastOnce()).setAttribute("login", user.getLogin());
        verify(req, atLeastOnce()).getRequestDispatcher("/home");
        verify(dispatcher, atLeastOnce()).forward(req, resp);
    }
}