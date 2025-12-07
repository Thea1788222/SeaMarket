package com.example.seamarket.controller;

import com.example.seamarket.model.User;
import com.example.seamarket.service.UserService;
import com.example.seamarket.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("logout".equals(action)) {
            req.getSession().invalidate();
            resp.sendRedirect("user/login.jsp");
            return;
        }

        // 默认跳登录
        resp.sendRedirect("user/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        // 注册
        if ("register".equals(action)) {
            handleRegister(req, resp);
            return;
        }

        // 登录
        if ("login".equals(action)) {
            handleLogin(req, resp);
            return;
        }
    }

    private void handleRegister(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        boolean ok = userService.register(username, password, email);

        if (ok) {
            resp.sendRedirect("user/login.jsp?success=1");
        } else {
            req.setAttribute("error", "用户名或邮箱已被使用");
            req.getRequestDispatcher("user/register.jsp").forward(req, resp);
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userService.login(email, password);

        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("error", "邮箱或密码错误");
            req.getRequestDispatcher("user/login.jsp").forward(req, resp);
        }
    }
}
