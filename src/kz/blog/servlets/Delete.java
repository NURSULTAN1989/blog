package kz.blog.servlets;

import kz.blog.db.Blogs;
import kz.blog.db.DBManager;
import kz.blog.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class Delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect="/";
        Users current_user=(Users)request.getSession().getAttribute("CURRENT_USER");
        if (current_user!=null){
            Long id=Long.parseLong(request.getParameter("id"));
            Blogs blogs= DBManager.getBlog(id);
            if (blogs.getUser().getId()==current_user.getId()){
                DBManager.deleteBlog(blogs);
                redirect="/";
            }

        }
        response.sendRedirect(redirect);
    }
}
