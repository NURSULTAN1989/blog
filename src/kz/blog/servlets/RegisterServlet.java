package kz.blog.servlets;

import kz.blog.db.DBManager;
import kz.blog.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String re_password=request.getParameter("re_password");
        String fullname=request.getParameter("full_name");
        String redirect="/register?error";
        if (    email!=null && !email.trim().equals("") &&
                        password!=null&&!password.trim().equals("")&&
                        re_password!=null&&!re_password.trim().equals("")&&
                        fullname!=null&&!fullname.trim().equals("")){
            Users users=DBManager.getUser(email);
            if (users==null){
                if (password.equals(re_password)){
                    DBManager.addUser(new Users(null,email,password,fullname));
                    redirect="/register?success";
                }
            }

        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request,response);
    }
}
