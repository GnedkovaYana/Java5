package org.example.servlet;

import org.example.service.AccountService;
import org.example.accounts.UserProfile;
import org.example.service.FileService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/registration")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = (String)req.getSession().getAttribute("login");
        String password = (String)req.getSession().getAttribute("password");
        if (login != null && password != null) {
            resp.sendRedirect("/files");
            return;
        }
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (login.isEmpty() || email.isEmpty() || password.isEmpty()) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Отсутствует значение одного или нескольких полей");
            return;
        }

        UserProfile userProfile = new UserProfile(login, password, email);
        AccountService.addNewUser(userProfile);
        String session = req.getSession().getId();
        AccountService.addNewSession(session, userProfile);
        File directory = new File("D:/filemanager/"+login);
        if (!directory.mkdir()) {
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("<h1 style='color: red;'>" +
                    "Ошибка создания профиля, возможно, пользователь с таким логином уже существует," +
                    " <a href='javascript:history.go(-1)'>назад </a></h1>");
            return;
        }

        //String url = req.getRequestURL().toString();
        //resp.sendRedirect( url.substring(0, url.lastIndexOf("/")) + "/files");
        resp.sendRedirect("/files");
    }
}