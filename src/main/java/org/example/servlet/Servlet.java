package org.example.servlet;
import org.example.accounts.UserProfile;
import org.example.service.AccountService;
import org.example.service.FileService;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/files")
public class Servlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String login = (String) request.getSession().getAttribute("login");
        String password = (String) request.getSession().getAttribute("password");
        if (login == null || password == null) {
            //String url = request.getRequestURL().toString();
            //response.sendRedirect( url.substring(0, url.lastIndexOf("/")) + "/login");
            response.sendRedirect("/login");
            return;
        }

        String path = request.getParameter("path");
        if (path == null || path.equals("D:\\filemanager")) {
            path = "D:\\filemanager\\"+ login;
        }
        if(!path.startsWith("D:\\filemanager\\" + login)){
            path = "D:\\filemanager\\" + login;
        }

        FileService fileService = new FileService();

        File[] directories = fileService.getDirectories(path);
        if (directories == null) {
            directories = new File[0];
        }

        File[] files = fileService.getFiles(path);
        if (files == null) {
            files = new File[0];
        }

        request.setAttribute("path", path);
        request.setAttribute("directories", directories);
        request.setAttribute("files", files);
        request.getRequestDispatcher("servlet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        req.getSession().removeAttribute("login");
        req.getSession().removeAttribute("password");

        //String url = req.getRequestURL().toString();
        //resp.sendRedirect( url.substring(0, url.lastIndexOf("/")) + "/login");
        resp.sendRedirect("/login");
    }
}