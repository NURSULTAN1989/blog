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

@WebServlet("/edit")
public class EditBlog extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect="/";
        Users current_user=(Users)request.getSession().getAttribute("CURRENT_USER");
        if (current_user!=null){
            Long id=Long.parseLong(request.getParameter("id"));
            String title=request.getParameter("title");
            String short_content=request.getParameter("short_content");
            String content=request.getParameter("content");
            if (id!=null && title != null && short_content != null && content != null) {
                Blogs blogs=DBManager.getBlog(id);
                if (blogs.getUser().getId()==current_user.getId()){
                    blogs.setTitle(title);
                    blogs.setShortContent(short_content);
                    blogs.setContent(content);
                    DBManager.updateBlog(blogs);
                    redirect="/readblog?id="+id+"&success";
                }

            }
        }
        response.sendRedirect(redirect);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users current_user=(Users)request.getSession().getAttribute("CURRENT_USER");
        if (current_user!=null){
            Long id=Long.parseLong(request.getParameter("id"));
            Blogs blogs= DBManager.getBlog(id);
            if (blogs!=null && current_user.getId()==blogs.getUser().getId()){

                request.setAttribute("blogs",blogs);
                request.getRequestDispatcher("editblog.jsp").forward(request,response);

            }else{
                request.getRequestDispatcher("404.jsp").forward(request,response);
            }
        } else response.sendRedirect("/");

    }
}
