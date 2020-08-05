<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.blog.db.Blogs" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
  <head>
    <%@include file="templates/head.jsp"%>
  </head>
  <body>

    <div class="container">
      <%@include file="templates/navbar.jsp"%>
    </div>
    <div class="container">
      <div class="row mt-4">
        <div class="col-sm-12">
          <%
            if(key!=null){
          %>
          <h3>Search results for key : "<%=key%>"</h3>
          <br>
          <%
            }
          %>
          <%
            ArrayList<Blogs> blogs = (ArrayList<Blogs>)request.getAttribute("blogs");
            if(blogs!=null){
              for(Blogs b : blogs){
          %>
          <div class="jumbotron">
            <h4>
              <%=b.getTitle()%>
            </h4>
            <p class="lead">
              <%=b.getShortContent()%>
            </p>
            <hr class="my-4">
            <p>Posted by <%=b.getUser().getFullname()%> at <%=b.getPostDate()%></p>
            <a class="btn btn-primary btn-sm" href="/readblog?id=<%=b.getId()%>" role="button">Read more</a>
          </div>
          <%
              }
            }
          %>

        </div>
      </div>
    </div>
    <%@include file="templates/scripts.jsp"%>
  </body>
</html>