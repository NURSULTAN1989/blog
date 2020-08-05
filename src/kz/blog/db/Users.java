package kz.blog.db;

public class Users {
    private Long id;
    private  String email;
    private String password;
    private String fullname;

    public Users(Long id, String email, String password, String fullname) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }

    public Users() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
