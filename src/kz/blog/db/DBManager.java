package kz.blog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/blog?useUnicode=true&serverTimezone=UTC", "root", "");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Users> getAllUsers(){
        ArrayList<Users> users=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM users ");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                users.add(new Users(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("fullname")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
    public static Users getUser(String email){
        Users users=null;
        try { PreparedStatement statement=connection.prepareStatement("SELECT * FROM users WHERE email=?");
            statement.setString(1, email);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){
                users=new Users(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("fullname")
                        );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
    public static void addUser(Users users){
        try {
            PreparedStatement statement=connection.prepareStatement("INSERT INTO users(email,password,fullname) VALUES (?,?,?)");
            statement.setString(1,users.getEmail());
            statement.setString(2,users.getPassword());
            statement.setString(3,users.getFullname());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void addBlog(Blogs blog){

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO blogs(user_id, title, short_content, content, post_date) " +
                    "VALUES (?, ?, ?, ?, NOW())");

            statement.setLong(1, blog.getUser().getId());
            statement.setString(2, blog.getTitle());
            statement.setString(3, blog.getShortContent());
            statement.setString(4, blog.getContent());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void updateBlog(Blogs blog){

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE blogs SET title = ?, short_content = ?, content = ? " +
                    "WHERE id = ?");


            statement.setString(1, blog.getTitle());
            statement.setString(2, blog.getShortContent());
            statement.setString(3, blog.getContent());
            statement.setLong(4, blog.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void deleteBlog(Blogs blog){

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM blogs WHERE id = ? AND user_id = ?");


            statement.setLong(1, blog.getId());
            statement.setLong(2, blog.getUser().getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static ArrayList<Blogs> getAllBlogs(){

        ArrayList<Blogs> allBlogs = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT b.id, b.title, b.short_content, b.content, b.post_date, b.user_id, u.fullname " +
                    "FROM blogs b INNER JOIN users u ON u.id = b.user_id " +
                    "ORDER BY b.post_date DESC ");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                allBlogs.add(new Blogs(
                        resultSet.getLong("id"),
                        new Users(
                                resultSet.getLong("user_id"),
                                null, null,
                                resultSet.getString("fullname")
                        ),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date")
                ));

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return allBlogs;
    }

    public static Blogs getBlog(Long id){

        Blogs blog = null;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT b.id, b.title, b.short_content, b.content, b.post_date, b.user_id, u.fullname " +
                    "FROM blogs b INNER JOIN users u ON u.id = b.user_id " +
                    "WHERE b.id = ? ");

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

                blog = new Blogs(
                        resultSet.getLong("id"),
                        new Users(
                                resultSet.getLong("user_id"),
                                null, null,
                                resultSet.getString("fullname")
                        ),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date")
                );

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return blog;
    }
    public static ArrayList<Blogs> searchBlogs(String key){

        ArrayList<Blogs> allBlogs = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT b.id, b.title, b.short_content, b.content, b.post_date, b.user_id, u.fullname " +
                    "FROM blogs b INNER JOIN users u ON u.id = b.user_id " +
                    "WHERE b.title LIKE ? OR b.short_content LIKE ? OR b.content LIKE ? " +
                    "ORDER BY b.post_date DESC ");

            statement.setString(1, "%"+key+"%");
            statement.setString(2, "%"+key+"%");
            statement.setString(3, "%"+key+"%");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                allBlogs.add(new Blogs(
                        resultSet.getLong("id"),
                        new Users(
                                resultSet.getLong("user_id"),
                                null, null,
                                resultSet.getString("fullname")
                        ),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date")
                ));

            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return allBlogs;
    }

    public static void addComment(Comments comment){

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO comments(user_id, blog_id, comment, post_date) " +
                    "VALUES (?, ?, ?, NOW())");

            statement.setLong(1, comment.getUser().getId());
            statement.setLong(2, comment.getBlogs().getId());
            statement.setString(3, comment.getComments());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static ArrayList<Comments> getAllCommentsByBlogId(Long id){

        ArrayList<Comments> comments = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.user_id, c.blog_id, c.comment, c.post_date, u.fullname " +
                    "FROM comments c " +
                    "LEFT OUTER JOIN users u ON u.id = c.user_id " +
                    "WHERE c.blog_id = ? " +
                    "ORDER BY c.post_date DESC ");

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                comments.add(new Comments(
                        resultSet.getLong("id"),
                        resultSet.getString("comment"),
                        new Users(
                                resultSet.getLong("user_id"),
                                null, null,
                                resultSet.getString("fullname")
                        ),
                        new Blogs(
                                resultSet.getLong("blog_id"),
                                null, null, null, null, null
                        ),
                        resultSet.getDate("post_date")
                ));
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return comments;

    }
}
