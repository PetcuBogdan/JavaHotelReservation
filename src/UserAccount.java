import java.sql.*;
import java.util.List;

public class UserAccount{
    private int id;
    private String username;
    private String name;
    private String password;
    private String email;
    private String phone;

    public int getId() {
        return id;
    }

    public UserAccount(int id, String username, String name, String password, String email, String phone) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void saveUser(){
        String jdbcUrl = "jdbc:mysql://localhost:3306/p3proiectdb";
        String username = "root";
        String password = "Petcu123";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "insert into users(username, password, name, email, phone)"+(" values (?, ?, ?, ?, ?)");
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, this.username);
            prepStmt.setString(2, this.password);
            prepStmt.setString(3, this.name);
            prepStmt.setString(4, this.email);
            prepStmt.setString(5, this.phone);

            prepStmt.execute();
            connection.close();
        }
        catch(Exception e){
            System.err.println("Got an exception!");
            // printStackTrace method
            // prints line numbers + call stack
            e.printStackTrace();
            // Prints what exception has been thrown
            System.out.println(e);
        }


    }
}
