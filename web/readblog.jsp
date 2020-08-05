<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.blog.db.Blogs" %>
<%@ page import="kz.blog.db.Comments" %>
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
      <div class="row mt-5">
        <div class="col-sm-12">

          <%
            Blogs blog = (Blogs) request.getAttribute("blog");
            if(blog!=null){
          %>
          <div class="jumbotron">
            <h4>
              <%=blog.getTitle()%>
            </h4>
            <p class="lead">
              <%=blog.getShortContent()%>
            </p>
            <p class="lead">
              <%=blog.getContent()%>
            </p>
            <hr class="my-4">
            <p>Posted by <%=blog.getUser().getFullname()%> at <%=blog.getPostDate()%></p>
            <% if (currentUser!=null){
              if (currentUser.getId()==blog.getUser().getId()){%>
            <a href="/edit?id=<%=blog.getId()%>" class="btn btn-info">EDIT</a>
            <%  }
              }
              %>
          </div>

          <%
            if(currentUser!=null){
          %>
          <form action="/addcomment" method="post">
            <div class="row mt-4">
              <div class="col-sm-12">
                <input type="hidden" name="blog_id" value="<%=blog.getId()%>">
                <textarea class="form-control" name="comment"></textarea>
                <button class="btn btn-success mt-3">ADD COMMENT</button>
              </div>
            </div>
          </form>
          <%
            }
          %>
          <div class="list-group mt-3">
            <% ArrayList<Comments> comments=(ArrayList<Comments>)request.getAttribute("comments");
            if (comments!=null) {
              for (Comments c: comments) {
            %>
            <a class="list-group-item list-group-item-action">
              <div class="d-flex w-100 justify-content-between">
                <small><%=c.getPost_date()%></small>
              </div>
              <p class="mb-1"><%=c.getComments()%></p>
              <small><%=c.getUser().getFullname()%></small>
            </a>
            <% } %>
          </div>

          <%  }
            }
          %>

        </div>
      </div>
    </div>
    <%@include file="templates/scripts.jsp"%>
  </body>
</html>