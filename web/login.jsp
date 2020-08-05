<%@ page import="java.util.ArrayList" %>
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
        <div class="col-sm-6 offset-3">
          <%
            String error = request.getParameter("error");
            if(error!=null){
          %>
            <div class="alert alert-danger">
              Couldn't register user
            </div>
          <%
            }
          %>
          <%
            String success = request.getParameter("success");
            if(success!=null){
          %>
          <div class="alert alert-success">
            User added successfully
          </div>
          <%
            }
          %>
          <form action="/login" method="post">
            <div class="form-group">
              <label>
                EMAIL:
              </label>
              <input class="form-control" type="email" name="email">
            </div>
            <div class="form-group">
              <label>
                PASSWORD:
              </label>
              <input class="form-control" type="password" name="password">
            </div>

            <div class="form-group">
              <button class="btn btn-success">SIGN IN</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <%@include file="templates/scripts.jsp"%>
  </body>
</html>