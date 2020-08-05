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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String key = request.getParameter("key");
        if(key!=null&&!key.equals("")){

            ArrayList<Blogs> blogs = DBManager.searchBlogs(key);
            request.setAttribute("blogs", blogs);

        }

        request.getRequestDispatcher("/search.jsp").forward(request,response);

    }
}
