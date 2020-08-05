package kz.blog.servlets;

import kz.blog.db.DBManager;
import kz.blog.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect="/login?error";
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        if (email!=null&&!email.trim().equals("")&&password!=null&&!password.trim().equals("")){
            Users foundUser= DBManager.getUser(email);
            if (foundUser!=null&&foundUser.getPassword().equals(password)){
                request.getSession().setAttribute("CURRENT_USER",foundUser);
                redirect="/profile";
            }
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
}
