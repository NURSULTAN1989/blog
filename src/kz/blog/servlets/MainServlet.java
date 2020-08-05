package kz.blog.servlets;

import kz.blog.db.Blogs;
import kz.blog.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Blogs> blogs = DBManager.getAllBlogs();
        request.setAttribute("blogs", blogs);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
