package servlets;

import models.User;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class LoginServletTest {

    private HttpServletRequest req = mock(HttpServletRequest.class);
    private HttpServletResponse resp = mock(HttpServletResponse.class);

    @Test
    public void testLoginServletDoPostForSuccessLogin() throws Exception {
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        User user = new User();
        user.setLogin("correctName");
        user.setPassword("correctPassword");

        when(req.getParameter("login")).thenReturn(user.getLogin());
        when(req.getParameter("password")).thenReturn(user.getPassword());

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(resp.getWriter()).thenReturn(writer);

        when(req.getRequestDispatcher("/home")).thenReturn(dispatcher);
        doNothing().when(dispatcher).forward(req, resp);

        new LoginServlet().doPost(req, resp);

        verify(req, atLeast(1)).getParameter(user.getLogin());
        verify(req, atLeast(1)).getParameter(user.getPassword());

        writer.flush();

        System.out.println(stringWriter);
    }
}