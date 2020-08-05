<%@ page import="kz.blog.db.Users" %>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: rgba(7,9,63,0.98);">
    <a class="navbar-brand" href="/">BBC.COM</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <%
            Users currentUser = (Users) request.getSession().getAttribute("CURRENT_USER");
            if(currentUser!=null){
        %>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/profile"><%=currentUser.getFullname()%></a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="/addblog">Add Blog</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            </ul>
        <%
            }else{
        %>
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="/register">Register</a>
                </li>
            </ul>
        <%
            }
        %>
        <%
            String key = request.getParameter("key");
        %>
        <form class="form-inline my-2 my-lg-0" method="get" action="/search">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" name="key" value="<%=(key!=null?key:"")%>">
            <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>