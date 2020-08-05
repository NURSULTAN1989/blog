package kz.blog.servlets;

import kz.blog.db.Blogs;
import kz.blog.db.Comments;
import kz.blog.db.DBManager;
import kz.blog.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addcomment")
public class AddComentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users current_user=(Users)request.getSession().getAttribute("CURRENT_USER");
        if (current_user!=null){
            Long id=Long.parseLong(request.getParameter("blog_id"));
            Blogs blogs= DBManager.getBlog(id);
            if (blogs!=null){
                String coment=request.getParameter("comment");
                DBManager.addComment(new Comments(null,coment,current_user,blogs,null));
                response.sendRedirect("/readblog?id="+id);
            }
        } else response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
