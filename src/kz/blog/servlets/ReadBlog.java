package kz.blog.servlets;


import kz.blog.db.Blogs;
import kz.blog.db.Comments;
import kz.blog.db.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/readblog")
public class ReadBlog extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        Blogs blog = DBManager.getBlog(id);

        if(blog!=null){
            ArrayList<Comments> comments=DBManager.getAllCommentsByBlogId(blog.getId());
            request.setAttribute("comments", comments);
            request.setAttribute("blog", blog);
            request.getRequestDispatcher("/readblog.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/404.jsp").forward(request, response);
        }

    }
}
