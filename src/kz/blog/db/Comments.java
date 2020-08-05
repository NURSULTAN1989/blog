package kz.blog.db;

import java.util.Date;

public class Comments {
    private Long id;
    private String comments;
    private Users user;
    private Blogs blogs;
    private Date post_date;

    public Comments() {
    }

    public Comments(Long id, String comments, Users user, Blogs blogs, Date post_date) {
        this.id = id;
        this.comments = comments;
        this.user = user;
        this.blogs = blogs;
        this.post_date = post_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Blogs getBlogs() {
        return blogs;
    }

    public void setBlogs(Blogs blogs) {
        this.blogs = blogs;
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }
}
